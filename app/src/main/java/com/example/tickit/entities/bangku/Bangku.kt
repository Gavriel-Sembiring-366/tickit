package com.example.tickit.entities.bangku

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bangku")
data class Bangku(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_bangku")
    val idBioskop: Int? = null,

    @ColumnInfo(name = "nomor_bangku")
    val namaBioskop: String? = null,

)
