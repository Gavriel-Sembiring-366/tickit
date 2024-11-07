package com.example.tickit.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: FilmRepository) : ViewModel() {

    private val _data = MutableLiveData<Film?>()
    val data: LiveData<Film?> get() = _data // Expose as LiveData instead of MutableLiveData

    fun getFilmById(id: Int) {
        viewModelScope.launch {
            val film = repository.getFilmById(id)
            _data.value = film
        }
    }
}