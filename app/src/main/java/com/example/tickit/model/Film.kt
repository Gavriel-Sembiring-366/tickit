package com.example.tickit.model



data class FilmResponse(
    val status: Int,
    val filmData: List<FilmData>?, // Change to List<FilmData> to support multiple records
    val message: String
)

data class FilmResponseSingular(
    val status: Int,
    val filmData: FilmData?,
    val message: String
)
data class FilmData(
    val film_id: String,
    val judul: String,
    val status_film: String,
    val genre_film:String,
    val durasi: String,
    val sinopsis: String,
    val sutradara: String,
    val tahun_rilis: String,
    val umur_rating: String
)