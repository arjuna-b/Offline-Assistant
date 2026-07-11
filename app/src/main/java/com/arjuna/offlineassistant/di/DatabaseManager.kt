package com.arjuna.offlineassistant.di

import android.content.Context
import androidx.room.Room
import com.arjuna.offlineassistant.data.ConversationDAO
import com.arjuna.offlineassistant.data.ConversationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseManager{
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : ConversationDatabase{
        return Room.databaseBuilder(context = context, ConversationDatabase::class.java,"database").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ConversationDatabase) : ConversationDAO{
        return database.dao()
    }
}