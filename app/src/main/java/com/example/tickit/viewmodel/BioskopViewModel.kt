package com.example.tickit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tickit.model.BioskopData
import com.example.tickit.model.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BioskopViewModel : ViewModel() {
    suspend fun getBioskopById(bioskopId: String): BioskopData {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getBioskopById(bioskopId)
                if (response.isSuccessful) {
                    response.body()?.bioskopData ?: BioskopData(
                        bioskop_id = "error",
                        nama_bioskop = "error",
                        alamat = "error",
                        kapasitas = 0
                    )
                } else {
                    BioskopData(
                        bioskop_id = "error",
                        nama_bioskop = "error",
                        alamat = "error",
                        kapasitas = 0
                    )
                }
            } catch (e: Exception) {
                BioskopData(
                    bioskop_id = "null",
                    nama_bioskop = "null",
                    alamat = "null",
                    kapasitas = 0
                )
            }
        }
    }
}
