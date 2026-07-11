package com.arjuna.offlineassistant.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Conversations::class, Messages::class], version = 2, exportSchema = false)
abstract class ConversationDatabase : RoomDatabase(){
    abstract fun dao() : ConversationDAO
}