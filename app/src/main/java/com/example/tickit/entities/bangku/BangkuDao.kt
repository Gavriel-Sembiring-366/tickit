package com.example.tickit.entities.bangku
import androidx.room.*


@Dao
interface BangkuDao {

    // Insert a new bangku into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bangku: Bangku)

    // Update an existing bangku
    @Update
    suspend fun update(bangku: Bangku)

    // Delete a bangku
    @Delete
    suspend fun delete(bangku: Bangku)

    // Get all bangku from the database
    @Query("SELECT * FROM bangku")
    suspend fun getAllBangku(): List<Bangku>

    // Get a single bangku by its ID
    @Query("SELECT * FROM bangku WHERE id_bangku = :idBangku")
    suspend fun getBangkuById(idBangku: Int): Bangku?
}
