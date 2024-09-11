package com.asensiodev.chat.domain.usecases

import com.asensiodev.chat.domain.models.Message
import com.asensiodev.chat.domain.repository.IMessagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveMessages @Inject constructor(
    private val repository: IMessagesRepository
) {
    suspend operator fun invoke(): Flow<Message> = repository.getMessages()
}