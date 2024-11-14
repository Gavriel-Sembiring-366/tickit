package com.example.tickit.entities.film

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
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
