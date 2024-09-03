package com.asensiodev.conversations.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.asensiodev.conversations.R
import com.asensiodev.conversations.model.Conversation
import com.asensiodev.conversations.ui.composables.ConversationList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsListScreen(
    onNewConversationClick: () -> Unit,
    onConversationClick: (chatId: String) -> Unit
) {
    val tabs = generateTabs()
    val selectedIndex = remember { mutableIntStateOf(1) }
    val pagerState = rememberPagerState(initialPage = 1) { 10 }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
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
        floatingActionButton = {
            FloatingActionButton(onClick = { /*onConversationClick()*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            contentPadding = innerPadding,
            //modifier = Modifier.padding(innerPadding),
            state = pagerState
        ) { index ->
            when (index) {
                0 -> {
                    // Status
                }

                1 -> {
                    ConversationList(
                        conversationList = generateFakeConversations(),
                        onConversationClick = onConversationClick
                    )
                }

                2 -> {
                    // Calls
                }
            }
        }

        LaunchedEffect(selectedIndex.value) {
            pagerState.animateScrollToPage(selectedIndex.value)
        }
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

fun generateFakeConversations(): List<Conversation> {
    return listOf(
        Conversation(
            id = "1",
            name = "John Doe",
            message = "Hey, how are you?",
            timestamp = "10:30",
            avatar = "https://i.pravatar.cc/150?u=1",
            unreadCount = 2
        ),
        Conversation(
            id = "2",
            name = "Jane Smith",
            message = "Looking forward to the party!",
            timestamp = "11:15",
            avatar = "https://i.pravatar.cc/150?u=2"
        ),
        // Add more conversations here
        Conversation(
            id = "3",
            name = "Michael Johnson",
            message = "Did you finish the project?",
            timestamp = "9:45",
            avatar = "https://i.pravatar.cc/150?u=3",
            unreadCount = 3
        ),
        Conversation(
            id = "4",
            name = "Emma Brown",
            message = "Great job on the presentation!",
            timestamp = "12:20",
            avatar = "https://i.pravatar.cc/150?u=4"
        ),
        Conversation(
            id = "5",
            name = "Lucas Smith",
            message = "See you at the game later.",
            timestamp = "14:10",
            avatar = "https://i.pravatar.cc/150?u=5",
            unreadCount = 1
        ),
        Conversation(
            id = "6",
            name = "Sophia Johnson",
            message = "Let's meet for lunch tomorrow.",
            timestamp = "16:00",
            avatar = "https://i.pravatar.cc/150?u=6"
        ),
        Conversation(
            id = "7",
            name = "Olivia Brown",
            message = "Can you help me with the assignment?",
            timestamp = "18:30",
            avatar = "https://i.pravatar.cc/150?u=7",
            unreadCount = 5
        ),
        Conversation(
            id = "8",
            name = "Liam Williams",
            message = "I'll call you later.",
            timestamp = "19:15",
            avatar = "https://i.pravatar.cc/150?u=8"
        ),
        Conversation(
            id = "9",
            name = "Charlotte Johnson",
            message = "Don't forget the meeting tomorrow.",
            timestamp = "21:45",
            avatar = "https://i.pravatar.cc/150?u=9",
            unreadCount = 1
        ),
        Conversation(
            id = "10",
            name = "James Brown",
            message = "The movie was awesome!",
            timestamp = "23:00",
            avatar = "https://i.pravatar.cc/150?u=10"
        ),
        Conversation(
            id = "11",
            name = "Jake Smith",
            message = "Did you get the tickets?",
            timestamp = "23:50",
            avatar = "https://i.pravatar.cc/150?u=11"
        ),
        Conversation(
            id = "12",
            name = "John Peters",
            message = "Hey puchini!",
            timestamp = "02:50",
            avatar = "https://i.pravatar.cc/150?u=12"
        ),
        Conversation(
            id = "13",
            name = "Bruce Wayne",
            message = "Late",
            timestamp = "04:07",
            avatar = "https://i.pravatar.cc/150?u=13"
        ),
        Conversation(
            id = "14",
            name = "Peter Parker",
            message = "It's raining in Hanover",
            timestamp = "05:03",
            avatar = "https://i.pravatar.cc/150?u=14"
        ),
        Conversation(
            id = "15",
            name = "William Cody",
            message = "Let's go?",
            timestamp = "05:30",
            avatar = "https://i.pravatar.cc/150?u=15"
        )
    )
}