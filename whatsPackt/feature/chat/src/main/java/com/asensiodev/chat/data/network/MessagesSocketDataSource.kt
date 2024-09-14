package com.asensiodev.chat.data.network

import com.asensiodev.chat.domain.models.Message
import com.asensiodev.chat.domain.models.WebsocketMessageModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MessagesSocketDataSource @Inject constructor(
    private val httpClient: HttpClient
) {
    private lateinit var webSocketSession: DefaultWebSocketSession

    suspend fun connect(url: String): Flow<Message> {
        return flow {
            httpClient.webSocketSession { url(url) }
                .apply { webSocketSession = this }
                .incoming
                .receiveAsFlow()
                .map { frame -> webSocketSession.handleMessage(frame) }
                .filterNotNull().map { it.toDomain() }
        }
    }

    suspend fun sendMessage(message: Message) {
        val webSocketMessage = WebsocketMessageModel.fromDomain(message)
        webSocketSession.converter?.serialize(webSocketMessage)?.let {
            webSocketSession.send(it)
        }

    }

    suspend fun disconnect() {
        webSocketSession.close(CloseReason(CloseReason.Codes.NORMAL, "Disconnect"))
    }

    private suspend fun DefaultWebSocketSession.handleMessage(frame: Frame): WebSocketMessageModel? {
        return when (frame) {
            is Frame.Text -> {
                converter?.deserialize(frame)
            }

            is Frame.Close -> {
                disconnect()
                null
            }

            else -> null
        }
    }
}