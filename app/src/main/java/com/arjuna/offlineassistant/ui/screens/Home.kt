package com.arjuna.offlineassistant.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arjuna.offlineassistant.data.Messages
import com.arjuna.offlineassistant.ui.theme.LocalAppColors
import com.arjuna.offlineassistant.ui.viewModel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Home(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val conversations by viewModel.conversations.collectAsState()
    var animation by remember { mutableFloatStateOf(0f) }
    val lazyListState = rememberLazyListState()
    val state = viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var expandMenu by remember { mutableStateOf(false) }
    var newConversationName by remember { mutableStateOf("") }
    val liveMessages by viewModel.liveMessages.collectAsState(emptyList())
    val colors = LocalAppColors.current

    LaunchedEffect(liveMessages.size) {
        if (liveMessages.isNotEmpty()) {
            lazyListState.animateScrollToItem(liveMessages.lastIndex)
        }
    }

    LaunchedEffect(state.value.isBotThinking) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            animation = value
        }
    }

    when {

        !state.value.isModelReady -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(colors.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp), color = colors.primary)
                Text(
                    "Setting up the environment for you. Please wait...",
                    textAlign = TextAlign.Center,
                    color = colors.floatingMenuText,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        state.value.isInitialLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(colors.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp), color = colors.primary)
            }
        }

        state.value.noConversations -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(colors.background)
                    .padding(16.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "\uD83E\uDD16 Ready when you are. Ask me anything!",
                    textAlign = TextAlign.Center,
                    color = colors.floatingMenuText,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(22.dp))
                Button(
                    onClick = {
                        viewModel.addNewConversationFlag.value = true
                    }
                ) {
                    Text("Add New Conversation", fontSize = 18.sp, color = colors.floatingMenuText)
                }
            }
        }

        state.value.hadConversations -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .background(colors.background)
                    .imePadding() // moves content up when keyboard opens
            ) {
                // Content takes all remaining space
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .weight(1f)
                        .background(colors.background)
                        .padding(vertical = 16.dp)
                ) {
                    if (liveMessages.isEmpty()) {
                        item {
                            Text(
                                "\uD83D\uDC4B Hi there! What's on your mind?.",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(
                                        Alignment.CenterHorizontally
                                    )
                                    .padding(8.dp),
                                textAlign = TextAlign.Center,
                                color = colors.floatingMenuText
                            )
                        }
                    } else {
                        items(liveMessages) {
                            MessageBubble(it)
                        }
                    }

                    if (state.value.isBotThinking) {
                        item {
                            Text(
                                "Thinking . . .", fontSize = 12.sp, modifier = Modifier
                                    .padding(8.dp)
                                    .alpha(animation)
                            )
                        }
                    }
                }

                DropdownMenu(
                    expanded = expandMenu,
                    onDismissRequest = { expandMenu = false },
                    containerColor = colors.floatingMenuBackground
                ) {

                    conversations.forEach {
                        DropdownMenuItem(
                            onClick = {
                                viewModel.currentConversationId.value = it.id
                            },
                            text = {
                                Text(it.title, fontSize = 14.sp, color = colors.floatingMenuText)
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        viewModel.deleteConversation(viewModel.currentConversationId.value)
                                        delay(200)
                                        if (conversations.isNotEmpty()) {
                                            viewModel.currentConversationId.value =
                                                conversations[conversations.lastIndex].id
                                        } else {
                                            viewModel.currentConversationId.value = -1
                                        }
                                    }
                                }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        "delete conversation",
                                        tint = colors.floatingMenuText
                                    )
                                }
                            }
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            viewModel.addNewConversationFlag.value = true
                        },
                        text = {
                            Text(
                                text = "+ Add New Conversation",
                                fontSize = 12.sp,
                                color = colors.floatingMenuText
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            expandMenu = true
                        },
                        modifier = Modifier.size(48.dp),
                        enabled = true,

                        ) {
                        Icon(
                            Icons.AutoMirrored.Filled.LibraryBooks,
                            "add new chat",
                            tint = colors.primary
                        )
                    }
                    OutlinedTextField(
                        value = viewModel.userQuery.value,
                        onValueChange = { viewModel.userQuery.value = it },
                        singleLine = false,
                        shape = RoundedCornerShape(30),
                        modifier = Modifier
                            .weight(1f)
                            .border(0.dp, colors.bottomBarBackground, RoundedCornerShape(50))
                            .wrapContentHeight(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = colors.bottomBarBackground,
                            unfocusedContainerColor = colors.bottomBarBackground
                        )
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                viewModel.askQuery(viewModel.userQuery.value)
                            }
                        },
                        modifier = Modifier.size(48.dp),
                        enabled = viewModel.userQuery.value.trim().isNotEmpty()
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, "Ask", tint = colors.primary)
                    }
                }
            }


        }

    }

    if (viewModel.addNewConversationFlag.value) {
        AlertDialog(
            onDismissRequest = {
                newConversationName = ""
                expandMenu = false
                viewModel.addNewConversationFlag.value = false
            },
            containerColor = colors.floatingMenuBackground,
            title = {
                Text("Add New Conversation", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colors.floatingMenuText)
            },
            text = {
                OutlinedTextField(
                    value = newConversationName,
                    onValueChange = { newConversationName = it },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newConversationName.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Conversation name can not be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            expandMenu = false
                            scope.launch {
                                viewModel.createNewConversation(newConversationName)
                                newConversationName = ""
                                delay(200)
                                if (conversations.isNotEmpty()) {
                                    viewModel.currentConversationId.value =
                                        conversations[conversations.lastIndex].id
                                }
                                viewModel.addNewConversationFlag.value = false
                            }
                        }
                    }
                ) {
                    Text("Add", color = colors.primary)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        newConversationName = ""
                        viewModel.addNewConversationFlag.value = false
                    }
                ) {
                    Text("Cancel", color = colors.floatingMenuText)
                }
            }
        )
    }

}

@Composable
fun MessageBubble(msg: Messages) {
    val colors = LocalAppColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement =
            if (msg.sender == "user")
                Arrangement.End
            else
                Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = if (msg.sender == "user") colors.userBubblebackground else colors.botBubblebackground
        ) {
            Text(
                text = msg.message,
                modifier = Modifier.padding(12.dp),
                color = if (msg.sender == "user") colors.userBubbleText else colors.botBubbleText
            )
        }
    }
}





