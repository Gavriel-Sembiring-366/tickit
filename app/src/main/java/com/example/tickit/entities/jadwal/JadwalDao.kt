package com.example.tickit.entities.jadwal
import androidx.room.*


@Dao
interface JadwalDao {

    // Insert a new jadwal into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jadwal: Jadwal)

    // Update an existing jadwal
    @Update
    suspend fun update(jadwal: Jadwal)

    // Delete a jadwal
    @Delete
    suspend fun delete(jadwal: Jadwal)

    // Get all films from the database
    @Query("SELECT * FROM jadwal")
    suspend fun getAllJadwal(): List<Jadwal>

    // Get a single jadwal by its ID
    @Query("SELECT * FROM jadwal WHERE id_jadwal = :idJadwal")
    suspend fun getJadwalById(idJadwal: Int): Jadwal?

    // Get films by id_film
    @Query("SELECT * FROM jadwal WHERE id_film = :idFilm")
    suspend fun getJadwalByFilm(idFilm: Int): List<Jadwal>
}
