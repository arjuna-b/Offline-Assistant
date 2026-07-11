package com.arjuna.offlineassistant.ui.uiStates

data class HomeScreenUiState(
    val isBotThinking: Boolean = false,
    val isInitialLoading: Boolean = true,
    val noConversations : Boolean = false,
    val hadConversations : Boolean = false,
    val isModelReady : Boolean = false
    )