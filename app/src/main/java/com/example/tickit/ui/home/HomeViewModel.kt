package com.example.tickit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    private val _dataList = MutableLiveData<List<Film>?>()
    val dataList: LiveData<List<Film>?> get() = _dataList

    fun getFilmsByIds(ids: List<Int>) {
        viewModelScope.launch {
            val films = filmRepository.getFilmsByIds(ids)
            _dataList.value = films
        }
    }

    private val _highlightMovies = MutableLiveData<List<Film>?>()
    val highlightMovies: LiveData<List<Film>?> get() = _highlightMovies

    private val _onGoingMovies = MutableLiveData<List<Film>?>()
    val onGoingMovies: LiveData<List<Film>?> get() = _onGoingMovies

    fun getHighlightMovies(ids: List<Int>) {
        viewModelScope.launch {
            _highlightMovies.value = filmRepository.getFilmsByIds(ids)
        }
    }

    fun getOnGoingMovies(ids: List<Int>) {
        viewModelScope.launch {
            _onGoingMovies.value = filmRepository.getFilmsByIds(ids)
        }
    }



}