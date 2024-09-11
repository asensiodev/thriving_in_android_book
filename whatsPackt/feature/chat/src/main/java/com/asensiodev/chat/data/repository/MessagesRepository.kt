package com.asensiodev.chat.data.repository

import com.asensiodev.chat.data.network.MessagesSocketDataSource
import com.asensiodev.chat.domain.models.Message
import com.asensiodev.chat.domain.repository.IMessagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val dataSource: MessagesSocketDataSource
) : IMessagesRepository {

    override suspend fun getMessages(): Flow<Message> = dataSource.connect()

    override suspend fun sendMessage(message: Message) = dataSource.sendMessage(message)

    override suspend fun disconnect() = dataSource.disconnect()
}