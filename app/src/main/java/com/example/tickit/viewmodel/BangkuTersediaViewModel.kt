package com.example.tickit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tickit.model.BangkuTersediaData
import com.example.tickit.model.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BangkuTersediaViewModel : ViewModel() {
    suspend fun getBangkuTersediaByJadwalId(jadwalId: String): List<BangkuTersediaData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getBangkuTersediaByJadwalId(jadwalId)
                if (response.isSuccessful) {
                    response.body()?.bangkuTersediaData ?: emptyList()
                } else {
                    emptyList()
                }
            }  catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun deleteBangkuTersediaById(bangkuTersediaId: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteBangkuTersediaById(bangkuTersediaId)
                if (response.isSuccessful) {
                    response.body()?.message ?: "Deletion successful, but no message provided."
                } else {
                    "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                "Failed to delete: ${e.message}"
            }
        }
    }
}
