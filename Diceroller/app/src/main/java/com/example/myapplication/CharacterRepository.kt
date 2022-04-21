package com.example.myapplication

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow



class CharacterRepository(private val characterDao: CharacterDao) {



    val allCharacters: Flow<List<Characterdata>> = characterDao.getAlphabetizedWords()
    //val oneCharacter: Flow<List<Characterdata>> = characterDao.loadCharacter(A)


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(characterdata: Characterdata) {
        characterDao.insert(characterdata)
    }
    @WorkerThread
    fun deleteByUserId(id: Int) {
        characterDao.deleteByUserId(id)
    }
    @WorkerThread
    fun updateByUserId(characterdata : Characterdata) {
        characterDao.updateByUserId(characterdata)
    }
    @WorkerThread
    fun loadCharacter(CharacterNum: Int): List<Characterdata> {
        return characterDao.loadCharacter(CharacterNum)

    }


}
