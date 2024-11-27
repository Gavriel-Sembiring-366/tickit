package com.example.tickit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private val currentFilmId = MutableLiveData<String>()

    fun getCurrentFilmId(): LiveData<String> {
        return currentFilmId
    }

    fun setCurrentFilmId(data: String) {
        currentFilmId.value = data
    }
}
