package com.asensiodev.whatspackt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.asensiodev.chat.ui.ChatScreen
import com.asensiodev.conversations.ui.ConversationsListScreen
import com.asensiodev.create_chat.ui.CreateConversationScreen
import com.asensiodev.framework.navigation.Chat
import com.asensiodev.framework.navigation.ConversationsList
import com.asensiodev.framework.navigation.NewConversation

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ConversationsList
    ) {
        addConversationsList(navController)
        addNewConversation(navController)
        addChat(navController)
    }
}

fun NavGraphBuilder.addConversationsList(navController: NavHostController) {
    composable<ConversationsList> {
        ConversationsListScreen(
            onNewConversationClick = {
                navController.navigate(NewConversation)
            },
            onConversationClick = { chatId ->
                navController.navigate(Chat(chatId))
            }
        )
    }
}

fun NavGraphBuilder.addNewConversation(navController: NavHostController) {
    composable<NewConversation> {
        CreateConversationScreen(
            onCreateConversation = {
                navController.navigate(Chat())
            }
        )
    }
}

fun NavGraphBuilder.addChat(navController: NavHostController) {
    composable<Chat> { backStackEntry ->
        val chat = backStackEntry.toRoute<Chat>()
        ChatScreen(
            chatId = chat.chatId,
            onBack = {
                navController.popBackStack()
            }
        )
    }
}
