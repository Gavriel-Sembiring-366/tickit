package com.example.tickit.entities.bangkuTerpakai

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.tickit.entities.bangku.Bangku
import com.example.tickit.entities.jadwal.Jadwal

@Entity(
        tableName = "bangku_terpakai",
        foreignKeys = [
                ForeignKey(
                        entity = Jadwal::class,
                        parentColumns = ["id_jadwal"],
                        childColumns = ["id_jadwal"]
                ),
                ForeignKey(
                        entity = Bangku::class,
                        parentColumns = ["id_bangku"],
                        childColumns = ["id_bangku"]
                )
        ]
)
data class BangkuTerpakai (

        @PrimaryKey(autoGenerate = true)

        @ColumnInfo(name = "id_bangku_terpakai")
        val idBangkuTerpakai: Int? = null,

        @ColumnInfo(name = "id_jadwal")
        val idJadwal: Int? = null,

        @ColumnInfo(name = "id_bangku")
        val idBangku: Int? = null,










)