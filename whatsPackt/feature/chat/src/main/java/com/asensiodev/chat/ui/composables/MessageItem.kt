package com.asensiodev.chat.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asensiodev.chat.models.Message
import com.asensiodev.chat.models.MessageContent
import com.asensiodev.framework.ui.Avatar

@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isMine) {
            Avatar(
                imageUrl = message.senderAvatar,
                size = 40.dp,
                contentDescription = "${message.senderName}'s avatar"
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column {
            if (message.isMine) {
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                Text(
                    text = message.senderName,
                    fontWeight = FontWeight.Bold
                )
            }
            when (val content = message.messageContent) {
                is MessageContent.ImageMessage -> {
                    AsyncImage(
                        model = content.imageUrl,
                        contentDescription = content.contentDescription,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                is MessageContent.TextMessage -> {
                    val surfaceColor = if (message.isMine) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    }

                    val textColor = if (message.isMine) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        Color.White
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = surfaceColor
                    ) {
                        Text(
                            text = content.message,
                            modifier = Modifier.padding(8.dp),
                            color = textColor
                        )
                    }
                }
            }
            Text(
                text = message.timestamp,
                fontSize = 12.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewMessageItem() {
    MessageItem(
        message = Message(
            id = "voluptatum",
            senderName = "Marissa Tanner",
            senderAvatar = "deseruisse",
            timestamp = "eum",
            isMine = false,
            messageContent = MessageContent.TextMessage("Hellooo!")
        )
    )
}