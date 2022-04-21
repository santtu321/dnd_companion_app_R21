package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAlphabetizedWords(): Flow<List<Characterdata>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterdata : Characterdata)

    @Query("DELETE FROM character_table")
    suspend fun deleteAll()

    @Query("DELETE FROM character_table WHERE id = :id")
    fun deleteByUserId(id: Int)

    @Update
    fun updateByUserId(characterdata : Characterdata)

    @Query("SELECT * FROM character_table LIMIT 1 OFFSET :CharacterNum")
    fun loadCharacter(CharacterNum: Int): List<Characterdata>
}//LIMIT 1 OFFSET