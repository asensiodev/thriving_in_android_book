package com.asensiodev.conversations.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.asensiodev.conversations.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsListScreen(
    onNewConversationClick: () -> Unit,
    onConversationClick: (chatId: String) -> Unit
) {
    val tabs = generateTabs()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.conversations_list_title)) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        bottomBar = {
            TabRow(selectedTabIndex = 1) {
                tabs.forEachIndexed { index, _ ->
                    Tab(
                        text = { Text(stringResource(tabs[index].title)) },
                        selected = index == 1,
                        onClick = {}
                    )
                }
            }
        },
        floatingActionButton = {}
    ) {

    }
}

data class ConversationsListTab(
    @StringRes val title: Int
)

fun generateTabs(): List<ConversationsListTab> = listOf(
    ConversationsListTab(title = R.string.conversations_tab_status_title),
    ConversationsListTab(title = R.string.conversations_tab_chats_title),
    ConversationsListTab(title = R.string.conversations_tab_calls_title)
)