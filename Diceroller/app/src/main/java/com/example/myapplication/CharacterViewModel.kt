package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {


    val allCharacters: LiveData<List<Characterdata>> = repository.allCharacters.asLiveData()

    fun insert(characterdata: Characterdata) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(characterdata)
    }

    fun deleteByUserId(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteByUserId(id)
    }
    fun updateByUserId(characterdata : Characterdata) = CoroutineScope(Dispatchers.IO).launch {
        repository.updateByUserId(characterdata)
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
