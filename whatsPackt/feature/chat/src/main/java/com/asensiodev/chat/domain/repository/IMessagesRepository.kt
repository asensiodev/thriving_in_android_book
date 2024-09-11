package com.asensiodev.chat.domain.repository

import com.asensiodev.chat.domain.models.Message
import kotlinx.coroutines.flow.Flow


interface IMessagesRepository {
    suspend fun getMessages(): Flow<Message>

    suspend fun sendMessage(message: Message)

    suspend fun disconnect()
}