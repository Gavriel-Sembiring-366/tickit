package com.example.tickit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.model.FilmData
import kotlinx.coroutines.launch

class MovieDetailViewModel() : ViewModel() {



    private val _currentJadwalId = MutableLiveData<String>()
    val currentJadwalId: LiveData<String> get() = _currentJadwalId

    fun setJadwalId(jadwalId: String) {
        _currentJadwalId.value = jadwalId
    }

    private val _currentSelectedSeatsId = MutableLiveData<List<String>>()
    val currentSelectedSeatsId: LiveData<List<String>> get() = _currentSelectedSeatsId

    fun setSelectedSeatsId(selectedSeatsId: List<String>) {
        _currentSelectedSeatsId.value = selectedSeatsId
    }

    private val _currentSelectedSeatsNomor = MutableLiveData<List<String>>()
    val currentSelectedSeatsNomor: LiveData<List<String>> get() = _currentSelectedSeatsNomor

    fun setSelectedSeatsNomor(SeatsNomor: List<String>) {
        _currentSelectedSeatsNomor.value = SeatsNomor
    }

    private val _currentBioskopName = MutableLiveData<String>()
    val currentBioskopName: LiveData<String> get() = _currentBioskopName

    fun setCurrentBioskopName(bioskopName: String) {
        _currentBioskopName.value = bioskopName
    }

    private val _currentTanggalHari = MutableLiveData<String>()
    val currentTanggalHari: LiveData<String> get() = _currentTanggalHari

    fun setCurrentTanggalHari(tanggalHari: String) {
        _currentTanggalHari.value = tanggalHari
    }

    private val _currentJamJadwal = MutableLiveData<String>()
    val currentJamJadwal: LiveData<String> get() = _currentJamJadwal

    fun setCurrentJamJadwal(jamJadwal: String) {
        _currentJamJadwal.value = jamJadwal
    }

    private val _currentJadwalTanggalJam = MutableLiveData<String>()
    val currentJadwalTanggalJam: LiveData<String> get() = _currentJadwalTanggalJam

    fun setCurrentJadwalTanggalJam(JadwalTanggalJam: String) {
        _currentJadwalTanggalJam.value = JadwalTanggalJam
    }

    val currentFilmId = MutableLiveData<String>()
    fun getCurrentFilmId(): LiveData<String> {
        return currentFilmId
    }

    fun setCurrentFilmId(data: String) {
        currentFilmId.value = data
    }

}
