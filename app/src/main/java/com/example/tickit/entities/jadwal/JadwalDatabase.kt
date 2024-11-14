package com.example.tickit.entities.jadwal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tickit.entities.bioskop.Bioskop
import com.example.tickit.entities.bioskop.BioskopDao
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmDao

@Database(entities = [Jadwal::class, Film::class, Bioskop::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class JadwalDatabase : RoomDatabase() {

    abstract fun jadwalDao(): JadwalDao
    abstract fun filmDao(): FilmDao
    abstract fun bioskopDao(): BioskopDao

    companion object {
        @Volatile
        private var INSTANCE: JadwalDatabase? = null

        fun getInstance(context: Context): JadwalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JadwalDatabase::class.java,
                    "slebew.db"
                )
                    .fallbackToDestructiveMigration() // Automatically clears the database on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
