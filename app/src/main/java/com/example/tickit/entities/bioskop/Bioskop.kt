package com.example.tickit.entities.bioskop

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bioskop")
data class Bioskop(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_bioskop")
    val idBioskop: Int? = null,

    @ColumnInfo(name = "nama_bioskop")
    val namaBioskop: String? = null,

    @ColumnInfo(name = "alamat")
    val alamat: String? = null,

    @ColumnInfo(name = "kapasitas")
    val kapasitas: Int? = null,
)
