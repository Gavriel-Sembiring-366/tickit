package com.example.tickit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tickit.entities.film.FilmRepository

class MovieDetailViewModelFactory(private val repository: FilmRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
