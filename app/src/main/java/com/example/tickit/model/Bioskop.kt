package com.example.tickit.model

data class BioskopData(
    val bioskop_id:String,
    val nama_bioskop: String,
    val alamat: String,
    val kapasitas:Int
)

data class BioskopResponseList(
    val status: Int,
    val bioskopData: List<BioskopData>,
    val message: String
)

data class BioskopResponseSingular(
    val status: Int,
    val bioskopData: BioskopData,
    val message: String
)