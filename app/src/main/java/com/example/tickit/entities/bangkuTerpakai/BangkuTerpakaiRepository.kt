package com.example.tickit.entities.bangkuTerpakai

import android.content.Context
import com.example.tickit.database.TickItDatabase
import com.example.tickit.entities.bangku.Bangku
import com.example.tickit.entities.bangku.BangkuDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BangkuTerpakaiRepository(context: Context) {

    private val bangkuTerpakaiDao: BangkuTerpakaiDao
    private val db = TickItDatabase.getDatabase(context)

    init {
        bangkuTerpakaiDao = db.bangkuTerpakaiDao()
    }

    // Insert a new bangkuTerpakai into the database
    suspend fun insert(bangkuTerpakai: BangkuTerpakai) = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.insert(bangkuTerpakai)
    }

    // Update an existing bangkuTerpakai
    suspend fun update(bangkuTerpakai: BangkuTerpakai) = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.update(bangkuTerpakai)
    }

    // Delete a bangkuTerpakai from the database
    suspend fun delete(bangkuTerpakai: BangkuTerpakai) = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.delete(bangkuTerpakai)
    }

    // Get all bangkuTerpakai
    suspend fun getAllBangkuTerpakai(): List<BangkuTerpakai> = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.getAllBangkuTerpakai()
    }

    suspend fun getBangkuTerpakaiByJadwal(idJadwal: Int): BangkuTerpakai? = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.getBangkuTerpakaiByJadwal(idJadwal)
    }

    // Get a bangku by its ID
    suspend fun getBangkuTerpakaiById(id: Int): BangkuTerpakai? = withContext(Dispatchers.IO) {
        bangkuTerpakaiDao.getBangkuTerpakaiById(id)

    }
}