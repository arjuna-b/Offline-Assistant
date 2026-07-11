package com.arjuna.offlineassistant.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "Table_Conversations")
data class Conversations(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)

@Entity(
    tableName = "Table_Messages", foreignKeys = [
        ForeignKey(
            entity = Conversations::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["chatId"])
    ]
)
data class Messages(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val chatId: Int,
    val sender: String,
    val message: String
)

data class ConversationWithMessages(
    @Embedded
    val conversation: Conversations,

    @Relation(
        parentColumn = "id",
        entityColumn = "chatId"
    )
    val msg: List<Messages>

)

