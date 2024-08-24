package com.asensiodev.framework.navigation

import kotlinx.serialization.Serializable


@Serializable
object NewConversation

@Serializable
object ConversationsList

@Serializable
data class Chat(val chatId: String? = null)

//object NavRoutes {
//    const val ConversationsList = "conversations_list"
//    const val NewConversation = "create_conversation"
//    const val Chat = "chat/{chatId}"
//
//    object ChatArgs {
//        const val ChatId = "chatId"
//    }
//}