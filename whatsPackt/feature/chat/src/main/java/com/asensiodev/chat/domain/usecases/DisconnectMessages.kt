package com.asensiodev.chat.domain.usecases

import com.asensiodev.chat.domain.repository.IMessagesRepository
import javax.inject.Inject

class DisconnectMessages @Inject constructor(
    private val repository: IMessagesRepository
) {
    suspend operator fun invoke() = repository.disconnect()
}