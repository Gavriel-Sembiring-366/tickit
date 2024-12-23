package com.example.tickit.model
import java.time.LocalDateTime


data class JadwalResponseList (
    val status: Int,
    val message: String,
    val jadwalData: List<JadwalData>?,

)

data class JadwalData (
    val jadwal_id: String,
    val film_id: String,
    val bioskop_id: String,
    val waktu_tayang: String
)