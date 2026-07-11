package com.arjuna.offlineassistant.data

import com.arjuna.offlineassistant.di.GemmaManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConversationRepository @Inject constructor(
    private val gemmaManager: GemmaManager,
    private val dao: ConversationDAO
) {
    suspend fun createConversation(title: String){
        dao.insertConversation(Conversations(title= title))
    }

    suspend fun deleteConversation(id:Int){
        dao.deleteConversation(id)
    }

    suspend fun askQuery(userQuery: String,chatId: Int){
        dao.insertMessages(Messages(chatId = chatId, message = userQuery, sender = "user"))
        val result = gemmaManager.generate(userQuery)
        dao.insertMessages(Messages(chatId = chatId, message = result, sender = "bot"))
    }

    fun getLiveMessages(chatId: Int): Flow<List<Messages>>{
        return dao.getMessagesOfConversation(chatId.toLong())
    }

    fun getConversationsList():Flow<List<Conversations>>{
        return dao.getAllConversations()
    }
    suspend fun getLastConversationId() : Int{
        val last = dao.getLastConversation()
        return last?.id ?: -1
    }
}