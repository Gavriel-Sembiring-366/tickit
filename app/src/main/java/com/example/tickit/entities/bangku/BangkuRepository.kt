package com.example.tickit.entities.bangku
import android.content.Context
import com.example.tickit.database.TickItDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BangkuRepository(context: Context) {

    private val bangkuDao: BangkuDao
    private val db = TickItDatabase.getDatabase(context)

    init {
        bangkuDao = db.bangkuDao()
    }

    // Insert a new bangku into the database
    suspend fun insert(bangku: Bangku) = withContext(Dispatchers.IO) {
        bangkuDao.insert(bangku)
    }

    // Update an existing bangku
    suspend fun update(bangku: Bangku) = withContext(Dispatchers.IO) {
        bangkuDao.update(bangku)
    }

    // Delete a bangku from the database
    suspend fun delete(bangku: Bangku) = withContext(Dispatchers.IO) {
        bangkuDao.delete(bangku)
    }

    // Get all bangkus
    suspend fun getAllBangkus(): List<Bangku> = withContext(Dispatchers.IO) {
        bangkuDao.getAllBangku()
    }

    // Get a bangku by its ID
    suspend fun getBangkuById(id: Int): Bangku? = withContext(Dispatchers.IO) {
        bangkuDao.getBangkuById(id)
    }

}
