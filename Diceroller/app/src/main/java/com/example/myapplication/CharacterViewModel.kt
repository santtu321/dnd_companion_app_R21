package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {


    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCharacters: LiveData<List<Characterdata>> = repository.allCharacters.asLiveData()
    //val loadCharacter: Flow<List<Characterdata>> = repository.oneCharacter
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(characterdata: Characterdata) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(characterdata)


    }

    fun deleteByUserId(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteByUserId(id)


    }
    fun updateByUserId(characterdata : Characterdata) = CoroutineScope(Dispatchers.IO).launch {
        repository.updateByUserId(characterdata)


    }
    fun loadCharacter(firstHurdle: Int)  = CoroutineScope(Dispatchers.IO).launch {
        repository.loadCharacter(firstHurdle)


    }
}

class CharacterViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
