package com.mehmetboluk.noteapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "notes")
data class Note(
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "note")
    val note : String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}