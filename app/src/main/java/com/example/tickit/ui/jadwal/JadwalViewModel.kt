package com.example.tickit.ui.jadwal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.entities.jadwal.Jadwal
import com.example.tickit.entities.jadwal.JadwalRepository
import kotlinx.coroutines.launch

class JadwalViewModel(private val repository: JadwalRepository) : ViewModel() {

    private val _data = MutableLiveData<List<Jadwal>?>()
    val data: MutableLiveData<List<Jadwal>?> get() = _data


    fun getJadwalByFilm(id: Int) {
        viewModelScope.launch {
            val jadwal = repository.getJadwalByFilm(id)
            _data.value = jadwal
        }
    }
}