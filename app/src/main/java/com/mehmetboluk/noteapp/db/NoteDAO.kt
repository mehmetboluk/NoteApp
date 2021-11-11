package com.mehmetboluk.noteapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note : Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("SELECT * FROM notes")
    fun getAll() : LiveData<List<Note>>
}