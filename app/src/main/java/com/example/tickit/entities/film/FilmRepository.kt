package com.example.tickit.entities.film
import android.content.Context
import androidx.room.Room
import com.example.tickit.database.TickItDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmRepository(context: Context) {

    private val filmDao: FilmDao
    private val db = TickItDatabase.getDatabase(context)

    init {
        filmDao = db.filmDao()
    }

    // Insert a new film into the database
    suspend fun insert(film: Film) = withContext(Dispatchers.IO) {
        filmDao.insert(film)
    }

    // Update an existing film
    suspend fun update(film: Film) = withContext(Dispatchers.IO) {
        filmDao.update(film)
    }

    // Delete a film from the database
    suspend fun delete(film: Film) = withContext(Dispatchers.IO) {
        filmDao.delete(film)
    }

    // Get all films
    suspend fun getAllFilms(): List<Film> = withContext(Dispatchers.IO) {
        filmDao.getAllFilms()
    }

    // Get a film by its ID
    suspend fun getFilmById(id: Int): Film? = withContext(Dispatchers.IO) {
        filmDao.getFilmById(id)
    }

    // Get films by genre
    suspend fun getFilmsByGenre(genre: String): List<Film> = withContext(Dispatchers.IO) {
        filmDao.getFilmsByGenre(genre)
    }
}
