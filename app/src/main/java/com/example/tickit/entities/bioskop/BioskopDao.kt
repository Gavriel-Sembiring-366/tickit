package com.example.tickit.entities.bioskop
import androidx.room.*


@Dao
interface BioskopDao {

    // Insert a new bioskop into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bioskop: Bioskop)

    // Update an existing bioskop
    @Update
    suspend fun update(bioskop: Bioskop)

    // Delete a bioskop
    @Delete
    suspend fun delete(bioskop: Bioskop)

    // Get all bioskops from the database
    @Query("SELECT * FROM bioskop")
    suspend fun getAllBioskops(): List<Bioskop>

    // Get a single bioskop by its ID
    @Query("SELECT * FROM bioskop WHERE id_bioskop = :idBioskop")
    suspend fun getBioskopById(idBioskop: Int): Bioskop?
}
