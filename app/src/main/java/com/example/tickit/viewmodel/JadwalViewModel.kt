package com.example.tickit.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.model.JadwalData
import com.example.tickit.model.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context

class JadwalViewModel() : ViewModel() {

    private val _jadwalDataById = MutableLiveData<List<JadwalData>>()
    val jadwalDataById: LiveData<List<JadwalData>> get() = _jadwalDataById

    private val _jadwalDataByFilmId = MutableLiveData<List<JadwalData>>()
    val jadwalDataByFilmId: MutableLiveData<List<JadwalData>> get() = _jadwalDataByFilmId

    fun fetchJadwalByFilmId(filmId: String, target: MutableLiveData<List<JadwalData>>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getJadwalByFilmId(filmId)
                if (response.isSuccessful) {
                    val jadwalData = response.body()?.jadwalData
                    withContext(Dispatchers.Main) {
                        if (jadwalData.isNullOrEmpty()) {
                        }
                        target.value = jadwalData
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        target.value = emptyList() // Handle failure
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    target.value = emptyList() // Handle error
                }
            }
        }
    }

    fun getJadwalByFilmId(filmId: String) {
        fetchJadwalByFilmId(filmId, _jadwalDataByFilmId)
    }
}
