package com.example.tickit.entities.bangkuTerpakai

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BangkuTerpakaiDao {

    // Insert a new bangku into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bangkuTerpakai: BangkuTerpakai)

    // Update an existing bangku
    @Update
    suspend fun update(bangkuTerpakai: BangkuTerpakai)

    // Delete a bangku
    @Delete
    suspend fun delete(bangkuTerpakai: BangkuTerpakai)

    // Get all bangku from the database
    @Query("SELECT * FROM bangku_terpakai")
    suspend fun getAllBangkuTerpakai(): List<BangkuTerpakai>

    // Get a single bangku by its ID
    @Query("SELECT * FROM bangku_terpakai WHERE id_bangku = :idBangkuTerpakai")
    suspend fun getBangkuTerpakaiById(idBangkuTerpakai: Int): BangkuTerpakai?

    @Query("SELECT * FROM bangku_terpakai WHERE id_jadwal = :idJadwal")
    suspend fun getBangkuTerpakaiByJadwal(idJadwal: Int): BangkuTerpakai?
}