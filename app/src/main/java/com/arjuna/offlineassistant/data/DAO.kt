package com.arjuna.offlineassistant.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDAO{
    @Insert
    suspend fun insertConversation(conversations: Conversations)

    @Insert
    suspend fun insertMessages(messages: Messages)

    @Query("DELETE FROM Table_Conversations WHERE id =:conversationID")
    suspend fun deleteConversation(conversationID: Int)

    @Query("SELECT * FROM Table_Messages WHERE chatId = :chatId")
    fun getMessagesOfConversation(chatId : Long) : Flow<List<Messages>>

    @Query("SELECT * FROM Table_Conversations")
    fun getAllConversations() : Flow<List<Conversations>>

    @Query("SELECT * FROM Table_Conversations ORDER BY id DESC LIMIT 1")
    suspend fun getLastConversation() : Conversations?
}