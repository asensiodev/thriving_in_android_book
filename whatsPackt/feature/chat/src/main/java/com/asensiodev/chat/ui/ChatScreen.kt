package com.asensiodev.chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asensiodev.chat.R
import com.asensiodev.chat.ui.composables.MessageItem
import com.asensiodev.chat.ui.model.Message
import com.asensiodev.chat.ui.model.MessageContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    chatId: String?,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadAndUpdateMessages()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.chat_title, "Alice"))
            })
        },
        bottomBar = {
            SendMessageBox() { viewModel.onSendMessage(it) }
        }
    ) { innerPadding ->
        ListOfMessages(paddingValues = innerPadding)
    }
}

@Composable
fun ListOfMessages(paddingValues: PaddingValues, messages: List<Message>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    MessageItem(message = message)
                }
            }
        }
    }
}

@Composable
fun SendMessageBox(sendMessage: (String) -> Unit) {
    Box(
        modifier = Modifier
            .defaultMinSize()
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .align(Alignment.CenterStart)
                .height(56.dp)
        )

        IconButton(
            onClick = {
                sendMessage(text)
                text = ""
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send message",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

fun getFakeMessages(): List<Message> {
    return listOf(
        Message(
            id = "1",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:00",
            messageContent = MessageContent.TextMessage(
                message = "Hi, how are you?"
            )
        ),
        Message(
            id = "2",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=2",
            isMine = true,
            timestamp = "10:01",
            messageContent = MessageContent.TextMessage(
                message = "I'm good, thank you! And you?"
            )
        ),
        Message(
            id = "3",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:02",
            messageContent = MessageContent.TextMessage(
                message = "Super fine!"
            )
        ),
        Message(
            id = "4",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = true,
            timestamp = "10:02",
            messageContent = MessageContent.TextMessage(
                message = "Are you going to the Kotlin conference?"
            )
        ),
        Message(
            id = "5",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:03",
            messageContent = MessageContent.TextMessage(
                message = "Of course! I hope to see you there!"
            )
        ),
        Message(
            id = "5",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:03",
            messageContent = MessageContent.TextMessage(
                message = "I'm going to arrive pretty early"
            )
        ),
        Message(
            id = "5",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:03",
            messageContent = MessageContent.TextMessage(
                message = "So maybe we can have a coffee together"
            )
        ),
        Message(
            id = "5",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "10:03",
            messageContent = MessageContent.TextMessage(
                message = "Wdyt?"
            )
        ),
    )
}