package com.asensiodev.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asensiodev.chat.domain.usecases.DisconnectMessages
import com.asensiodev.chat.domain.usecases.RetrieveMessages
import com.asensiodev.chat.domain.usecases.SendMessage
import com.asensiodev.chat.ui.model.Message
import com.asensiodev.chat.ui.model.MessageContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.asensiodev.chat.domain.models.Message as DomainMessage

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val retrieveMessages: RetrieveMessages,
    private val sendMessage: SendMessage,
    private val disconnectMessages: DisconnectMessages
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private var messageCollectionJob: Job? = null

    fun loadAndUpdateMessages() {
        messageCollectionJob = viewModelScope.launch(Dispatchers.IO) {
            retrieveMessages()
                .map { it.toUI() }
                .collect { message -> _messages.value += message }
        }
    }

    private fun DomainMessage.toUI(): Message {
        return Message(
            id = id ?: "",
            senderName = senderName,
            senderAvatar = senderAvatar,
            timestamp = timestamp ?: "",
            isMine = isMine,
            messageContent = getMessageContent()
        )
    }

    private fun DomainMessage.getMessageContent(): MessageContent {
        return when (contentType) {
            DomainMessage.ContentType.TEXT -> MessageContent.TextMessage(content)
            DomainMessage.ContentType.IMAGE -> MessageContent.ImageMessage(
                content,
                contentDescription
            )
        }
    }

    fun onSendMessage(messageText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = DomainMessage(
                senderAvatar = "",
                senderName = "",
                isMine = true,
                contentType = DomainMessage.ContentType.TEXT,
                content = messageText,
                contentDescription = messageText
            )
            sendMessage(message)
        }
    }

    override fun onCleared() {
        messageCollectionJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            disconnectMessages()
        }
    }
}