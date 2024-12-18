package com.example.tickit.entities.bioskop
import android.content.Context
import com.example.tickit.database.TickItDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BioskopRepository(context: Context) {

    private val bioskopDao: BioskopDao
    private val db = TickItDatabase.getDatabase(context)

    init {
        bioskopDao = db.bioskopDao()
    }

    // Insert a new bioskop into the database
    suspend fun insert(bioskop: Bioskop) = withContext(Dispatchers.IO) {
        bioskopDao.insert(bioskop)
    }

    // Update an existing bioskop
    suspend fun update(bioskop: Bioskop) = withContext(Dispatchers.IO) {
        bioskopDao.update(bioskop)
    }

    // Delete a bioskop from the database
    suspend fun delete(bioskop: Bioskop) = withContext(Dispatchers.IO) {
        bioskopDao.delete(bioskop)
    }

    // Get all bioskops
    suspend fun getAllBioskops(): List<Bioskop> = withContext(Dispatchers.IO) {
        bioskopDao.getAllBioskops()
    }

    // Get a bioskop by its ID
    suspend fun getBioskopById(id: Int): Bioskop? = withContext(Dispatchers.IO) {
        bioskopDao.getBioskopById(id)
    }

}
