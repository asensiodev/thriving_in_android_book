package com.asensiodev.chat.domain.usecases

import com.asensiodev.chat.domain.models.Message
import com.asensiodev.chat.domain.repository.IMessagesRepository
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val repository: IMessagesRepository
) {
    suspend operator fun invoke(message: Message) = repository.sendMessage(message)
}