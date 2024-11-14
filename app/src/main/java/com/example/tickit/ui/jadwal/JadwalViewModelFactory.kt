package com.example.tickit.ui.jadwal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.entities.jadwal.JadwalRepository
import com.example.tickit.ui.sinopsis.SinopsisViewModel

class JadwalViewModelFactory(private val repository: JadwalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JadwalViewModel::class.java)) {
            return JadwalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
