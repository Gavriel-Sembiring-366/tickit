package com.example.tickit


import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tickit.database.DBHelper
import com.example.tickit.database.MediaStoreHelper
import com.example.tickit.database.populatedata
import com.example.tickit.databinding.ActivityMovieDetailBinding
import com.example.tickit.entities.film.FilmDatabase
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.entities.jadwal.JadwalDatabase

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val repository by lazy { FilmRepository(applicationContext) }
    private val viewModel: MovieDetailViewModel by viewModels {MovieDetailViewModelFactory(repository)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_movie_detail)
        navView.setupWithNavController(navController)




        val filmDatabase = FilmDatabase.getInstance(this)
        val filmDao = filmDatabase.filmDao()

        val jadwalDatabase = JadwalDatabase.getInstance(this)
        val jadwalDao = jadwalDatabase.jadwalDao()


        val dbHelper = DBHelper(this, null)
        val db = dbHelper.writableDatabase
//        dbHelper.onCreate(db)
        populatedata().populateImage(this)
        viewModel.getFilmById(1)

        viewModel.data.observe(this) { film ->
            if (film != null) {
                binding.judulFilm.text = film.judul?.uppercase() ?: ""
                binding.genreFilm.text = film.genre?: ""
                binding.durasiFilm.text = buildString {
                    append(film.durasi.toString())
                    append(" menit")
                }
                binding.tahunRilisFilm.text = film.tahunRilis.toString()
                binding.umurRatingFilm.text = film.umurRating ?: ""


                val imageCarousel = (film.judul?.lowercase()?.replace(" ", "_") ?: "") + "_carousel.jpeg"
                val imageCarouselBitmap = MediaStoreHelper(this).getImageFromMediaStore(imageCarousel)
                imageCarouselBitmap?.let {
                    binding.moviePoster.setImageBitmap(it)
                }

                val imageCard = (film.judul?.lowercase()?.replace(" ", "_") ?: "") + "_card.jpeg"
                val imageCardBitmap = MediaStoreHelper(this).getImageFromMediaStore(imageCard)
                imageCardBitmap?.let {
                    binding.movieCardImage.setImageBitmap(it)
                }

            }
        }

        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setDisplayShowTitleEnabled(false)

            val customView = layoutInflater.inflate(R.layout.custom_action_bar, null)

            customView.findViewById<ImageView>(R.id.logoImage)?.setImageResource(R.drawable.tickit_logo)
            customView.findViewById<ImageView>(R.id.searchImage)?.setImageResource(R.drawable.search_svgrepo_com)
            customView.findViewById<ImageView>(R.id.userImage)?.setImageResource(R.drawable.user_circle_svgrepo_com)
            setCustomView(
                customView,
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }
}