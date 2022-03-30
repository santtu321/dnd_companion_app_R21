package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY character ASC")
    fun getAlphabetizedWords(): Flow<List<Character>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Query("DELETE FROM character_table")
    suspend fun deleteAll()
}