package com.example.tickit.entities.film
import androidx.room.*


@Dao
interface FilmDao {

    // Insert a new film into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Film)

    // Update an existing film
    @Update
    suspend fun update(film: Film)

    // Delete a film
    @Delete
    suspend fun delete(film: Film)

    // Get all films from the database
    @Query("SELECT * FROM film")
    suspend fun getAllFilms(): List<Film>

    // Get a single film by its ID
    @Query("SELECT * FROM film WHERE id_film = :idFilm")
    suspend fun getFilmById(idFilm: Int): Film?

    // Get films by genre
    @Query("SELECT * FROM film WHERE genre = :genre")
    suspend fun getFilmsByGenre(genre: String): List<Film>

    @Query("SELECT * FROM film WHERE id_film IN (:ids)")
    suspend fun getFilmsByIds(ids: List<Int>): List<Film>
}
