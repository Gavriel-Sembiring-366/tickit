package com.example.tickit.entities.jadwal
import android.content.Context
import androidx.room.Room
import com.example.tickit.database.DBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JadwalRepository(context: Context) {

    private val jadwalDao: JadwalDao
    private val jadwalDatabase: JadwalDatabase = Room.databaseBuilder(
        context.applicationContext,
        JadwalDatabase::class.java,
        "slebew.db"
    ).build()

    init {
        jadwalDao = jadwalDatabase.jadwalDao()
    }

    // Insert a new jadwal into the database
    suspend fun insert(jadwal: Jadwal) = withContext(Dispatchers.IO) {
        jadwalDao.insert(jadwal)
    }

    // Update an existing jadwal
    suspend fun update(jadwal: Jadwal) = withContext(Dispatchers.IO) {
        jadwalDao.update(jadwal)
    }

    // Delete a jadwal from the database
    suspend fun delete(jadwal: Jadwal) = withContext(Dispatchers.IO) {
        jadwalDao.delete(jadwal)
    }

    // Get all jadwal
    suspend fun getAllJadwal(): List<Jadwal> = withContext(Dispatchers.IO) {
        jadwalDao.getAllJadwal()
    }

    // Get a jadwal by its ID
    suspend fun getJadwalByFilm(id: Int): List<Jadwal> = withContext(Dispatchers.IO) {
        jadwalDao.getJadwalByFilm(id)
    }

}
