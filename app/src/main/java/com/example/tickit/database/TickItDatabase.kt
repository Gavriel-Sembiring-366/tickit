package com.example.tickit.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tickit.entities.bioskop.Bioskop
import com.example.tickit.entities.bioskop.BioskopDao
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmDao
import com.example.tickit.entities.jadwal.Jadwal
import com.example.tickit.entities.jadwal.JadwalDao


@Database(entities = [Bioskop::class, Jadwal::class, Film::class], version = 1)

abstract class TickItDatabase : RoomDatabase() {
    abstract fun bioskopDao(): BioskopDao
    abstract fun jadwalDao(): JadwalDao
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: TickItDatabase? = null

        fun getDatabase(context: Context): TickItDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TickItDatabase::class.java,
                    "slebew.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
