import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.model.FilmData
import com.example.tickit.model.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmViewModel : ViewModel() {

    private val _ongoingFilms = MutableLiveData<List<FilmData>>()
    val ongoingFilms: LiveData<List<FilmData>> get() = _ongoingFilms

    private val _highlightFilms = MutableLiveData<List<FilmData>>()
    val highlightFilms: LiveData<List<FilmData>> get() = _highlightFilms

    private val _comingSoonFilms = MutableLiveData<List<FilmData>>()
    val comingSoonFilms: LiveData<List<FilmData>> get() = _comingSoonFilms

    private val _filmDataById = MutableLiveData<FilmData>()
    val filmDataById: MutableLiveData<FilmData> get() = _filmDataById

    fun fetchFilmsByStatus(status: String, target: MutableLiveData<List<FilmData>>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getFilmsByStatus(status)
                if (response.isSuccessful) {
                    val film = response.body()?.filmData ?: emptyList()
                    withContext(Dispatchers.Main) {
                        target.value = film
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        target.value = emptyList() // Handle failure
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    target.value = emptyList() // Handle error
                }
            }
        }
    }

    fun fetchFilmById(filmId: String, target: MutableLiveData<FilmData>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getFilmById(filmId)
                if (response.isSuccessful) {
                    val filmData = response.body()?.filmData
                    withContext(Dispatchers.Main) {
                        target.value = filmData
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        target.value = FilmData(
                            film_id = "error",
                            judul = "error",
                            status_film = "error",
                            genre_film = "error",
                            durasi = "error",
                            sinopsis = "error",
                            sutradara = "error",
                            tahun_rilis = "error",
                            umur_rating = "error"
                        )

                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    target.value = FilmData(
                        film_id = "null",
                        judul = "null",
                        status_film = "null",
                        genre_film = "null",
                        durasi = "null",
                        sinopsis = "null",
                        sutradara = "null",
                        tahun_rilis = "null",
                        umur_rating = "null"
                    )
                }
            }
        }
    }

    fun fetchOngoingFilms() {
        fetchFilmsByStatus("ongoing", _ongoingFilms)
    }

    fun fetchHighlightFilms() {
        fetchFilmsByStatus("ongoing", _highlightFilms)
    }

    fun fetchComingSoonFilms() {
        fetchFilmsByStatus("ongoing", _comingSoonFilms)
    }

    fun getFilmById(filmId:String) {
        fetchFilmById(filmId, _filmDataById)
    }
}
