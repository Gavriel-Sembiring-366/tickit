package com.example.tickit.entities.bangkuTerpakai

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bangku_terpakai")
data class BangkuTerpakai (

        @PrimaryKey(autoGenerate = true)

        @ColumnInfo(name = "id_bangku_terpakai")
        val idBangkuTerpakai: Int? = null,

        @ColumnInfo(name = "id_bangku")
        val idBangku: Int? = null,

        @ColumnInfo(name = "id_jadwal")
        val idJadwal: Int? = null,




)