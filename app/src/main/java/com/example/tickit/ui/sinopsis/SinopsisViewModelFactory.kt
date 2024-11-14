package com.example.tickit.ui.sinopsis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tickit.entities.film.FilmRepository

class SinopsisViewModelFactory(private val repository: FilmRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SinopsisViewModel::class.java)) {
            return SinopsisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
