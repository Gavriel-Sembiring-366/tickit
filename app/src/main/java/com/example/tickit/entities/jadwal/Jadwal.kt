package com.example.tickit.entities.jadwal


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import com.example.tickit.entities.bioskop.Bioskop
import com.example.tickit.entities.film.Film
import java.util.Date

@Entity(
    tableName = "jadwal",
    foreignKeys = [
        ForeignKey(
            entity = Film::class,
            parentColumns = ["id_film"],
            childColumns = ["id_film"]
        ),
        ForeignKey(
            entity = Bioskop::class,
            parentColumns = ["id_bioskop"],
            childColumns = ["id_bioskop"]
        )
    ]
)

data class Jadwal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_jadwal")
    val idJadwal: Int? = null,

    @ColumnInfo(name = "id_film")
    val idFilm: Int? = null,

    @ColumnInfo(name = "id_bioskop")
    val idBioskop: Int? = null,

    @ColumnInfo(name = "waktu_tayang")
    val waktuTayang: String? = null,
)