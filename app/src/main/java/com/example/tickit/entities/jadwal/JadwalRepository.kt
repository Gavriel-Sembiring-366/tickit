package com.example.tickit.entities.jadwal
import android.content.Context
import androidx.room.Room
import com.example.tickit.database.DBHelper
import com.example.tickit.database.TickItDatabase
import com.example.tickit.entities.bioskop.BioskopDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JadwalRepository(context: Context) {

    private val jadwalDao: JadwalDao
    private val bioskopDao: BioskopDao
    private val db = TickItDatabase.getDatabase(context)

    init {
        jadwalDao = db.jadwalDao()
        bioskopDao = db.bioskopDao()
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

    suspend fun getJadwalByFilmAndBioskop(id_film: Int, id_bioskop: Int): List<Jadwal> = withContext(Dispatchers.IO) {
        jadwalDao.getJadwalByFilmAndBioskop(id_film, id_bioskop)
    }

    suspend fun getBioskopById(idBioskop: Int): String {
        val bioskop = bioskopDao.getBioskopById(idBioskop)
        return bioskop?.namaBioskop ?: "Unknown Bioskop"
    }
}
