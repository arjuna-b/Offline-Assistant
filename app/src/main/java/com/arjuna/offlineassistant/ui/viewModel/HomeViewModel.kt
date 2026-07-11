package com.arjuna.offlineassistant.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjuna.offlineassistant.data.ConversationRepository
import com.arjuna.offlineassistant.ui.uiStates.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ConversationRepository,
    @param:ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    var userQuery = mutableStateOf("")
    val currentConversationId = MutableStateFlow(-1)
    var addNewConversationFlag = mutableStateOf(false)


    val conversations = repository.getConversationsList()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), emptyList())
    val liveMessages = currentConversationId.flatMapLatest { id ->
        if (id >= 0) {
            Log.e("TAG-if", currentConversationId.value.toString())
            _uiState.value = _uiState.value.copy(hadConversations = true, noConversations = false)
            repository.getLiveMessages(id)
        } else {
            Log.e("TAG-else", currentConversationId.value.toString())
            _uiState.value = _uiState.value.copy(noConversations = true, hadConversations = false)
            flowOf(emptyList())
        }
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            if (copyModelToInternalStorage()){
                _uiState.value = _uiState.value.copy(isModelReady = true)
            }
        }

    }


    init {
        viewModelScope.launch {
            currentConversationId.value = repository.getLastConversationId()
            Log.e("TAG", currentConversationId.value.toString())
            _uiState.value = _uiState.value.copy(isInitialLoading = false)
        }
    }


    fun askQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isBotThinking = true)
            userQuery.value = ""
            Log.e("askQuery: ", query + currentConversationId.value)
            repository.askQuery(query, currentConversationId.value)
            _uiState.value = _uiState.value.copy(isBotThinking = false)
        }
    }

    suspend fun createNewConversation(title: String) {
        Log.d("createNewConversation: ", title)
        repository.createConversation(title)
    }

    suspend fun deleteConversation(id: Int) {
        repository.deleteConversation(id)
    }

    fun copyModelToInternalStorage(): Boolean {
        val file = File(
            context.filesDir,
            "gemma3-1b-it-int4.task"
        ) //checks if file already there in internal storage. true then returns path otherwise copy from assets and share the path

        if (!file.exists()) {
            try {
                context.assets.open("gemma3-1b-it-int4.task").use { inputStream ->
                    file.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                Log.d("copyModelToInternalStorage:Exception ","copied...✅")
                return true
            } catch (ex: Exception) {
                Log.e("copyModelToInternalStorage:Exception ", ex.toString())
                return false
            }
        }
        return true
    }
}