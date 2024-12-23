package com.example.tickit.model

data class BangkuTersediaData(
    val bangku_tersedia_id: String,
    val jadwal_id: String,
    val nomor_bangku: String
)

data class BangkuTersediaResponseSingular(
    val status: Int,
    val bangkuTersediaData: BangkuTersediaData?,
    val message: String
)

data class BangkuTersediaResponseList(
    val status: Int,
    val bangkuTersediaData: List<BangkuTersediaData>?,
    val message: String
)

data class BangkuTersediaDeleteResponse(
    val status: Int,
    val message: String,
    val data: BangkuTersediaData? // Adjust this based on the API response structure
)