package com.example.tickit.entities.film

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "film")
data class Film(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_film")
    val idFilm: Int? = null,

    @ColumnInfo(name = "judul")
    val judul: String? = null,

    @ColumnInfo(name = "genre")
    val genre: String? = null,

    @ColumnInfo(name = "durasi")
    val durasi: Int? = null,

    @ColumnInfo(name = "sinopsis")
    val sinopsis: String? = null,

    @ColumnInfo(name = "tahun_rilis")
    val tahunRilis: Int? = null,

    @ColumnInfo(name = "umur_rating")
    val umurRating: String? = null
)
