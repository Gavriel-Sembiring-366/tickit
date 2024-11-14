package com.example.tickit.entities.bioskop


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Bioskop::class], version = 1)

abstract class BioskopDatabase : RoomDatabase() {
    abstract fun bioskopDao(): BioskopDao
}
