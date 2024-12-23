package com.example.tickit


import FilmViewModel
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tickit.databinding.ActivityMovieDetailBinding
import com.example.tickit.entities.film.GetImgMimeName
import com.google.android.material.bottomnavigation.BottomNavigationView


class MovieDetail : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private val filmViewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.movieDetailBottomNav

        val navController = findNavController(R.id.nav_host_fragment_activity_movie_detail)
        navView.setupWithNavController(navController)

//        val dbHelper = DBHelper(this, null)
//        val db = dbHelper.writableDatabase
//        populatedata().populateMockData(db)
//        populatedata().populateImage(this)

        val extras = intent.extras
        if (extras != null) {
            val idFilm = extras.getString("idFilm")
            viewModel.setCurrentFilmId(idFilm.toString())
        }

        filmViewModel.getFilmById(viewModel.currentFilmId.value.toString())


        filmViewModel.filmDataById.observe(this) { film ->
            if (film != null) {
                binding.judulFilm.text = film.judul.uppercase() ?: ""
                binding.genreFilm.text = film.genre_film?: ""
                binding.durasiFilm.text = buildString {
                    append(film.durasi)
                    append(" menit")
                }
                binding.tahunRilisFilm.text = film.tahun_rilis
                binding.umurRatingFilm.text = film.umur_rating


                val imageLandscapeBitmap = GetImgMimeName(this).getImgMimeLandscapeName(film.judul?:"")
                imageLandscapeBitmap?.let {
                    binding.moviePoster.setImageBitmap(it)
                }

                val imagePotraitBitmap = GetImgMimeName(this).getImgMimePotraitName(film.judul?:"")
                imagePotraitBitmap?.let {
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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateMovieDetail(destination.id, binding.root)
            updateCustomActionBar(destination.id, binding.root)
        }
    }


    private fun updateCustomActionBar(destinationId: Int, rootView: View) {
        if (destinationId == R.id.navigation_kursi) {
            val customActionBar = layoutInflater.inflate(R.layout.kursi_fragment_action_bar, null)

            viewModel.currentBioskopName.observe(this) { bioskopName ->
                customActionBar.findViewById<TextView>(R.id.bioskopName)?.text = bioskopName
            }
            viewModel.currentTanggalHari.observe(this) { tanggalHari ->
                customActionBar.findViewById<TextView>(R.id.tanggalJadwal)?.text = tanggalHari
            }
            viewModel.currentJamJadwal.observe(this) { jamJadwal ->
                customActionBar.findViewById<TextView>(R.id.jamJadwal)?.text = jamJadwal
            }

            customActionBar.findViewById<ImageView>(R.id.timeImage)?.setImageResource(R.drawable.time)

            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            customActionBar.layoutParams = params


            val actionBar = (this as AppCompatActivity).supportActionBar
            actionBar?.apply {
                setCustomView(customActionBar)
                setDisplayShowCustomEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }else{
            val customActionBar = layoutInflater.inflate(R.layout.custom_action_bar, null)

            customActionBar.findViewById<ImageView>(R.id.logoImage)?.setImageResource(R.drawable.tickit_logo)
            customActionBar.findViewById<ImageView>(R.id.searchImage)?.setImageResource(R.drawable.search_svgrepo_com)
            customActionBar.findViewById<ImageView>(R.id.userImage)?.setImageResource(R.drawable.user_circle_svgrepo_com)

            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            customActionBar.layoutParams = params

            val actionBar = (this as AppCompatActivity).supportActionBar
            actionBar?.apply {
                setCustomView(customActionBar)
                setDisplayShowCustomEnabled(true)
                setDisplayShowTitleEnabled(false)
            }


        }
    }



    private fun updateMovieDetail(destinationId: Int, rootView: View) {
        val movieDesc = rootView.findViewById<ConstraintLayout>(R.id.movie_desc)
        val moviePoster = rootView.findViewById<ImageView>(R.id.movie_poster)
        val movieDetailBottomNav = rootView.findViewById<BottomNavigationView>(R.id.movie_detail_bottom_nav)

        if (destinationId == R.id.navigation_kursi || destinationId == R.id.navigation_pembayaran) {
            moviePoster?.visibility = View.GONE
            movieDesc?.visibility = View.GONE
            movieDetailBottomNav.visibility = View.GONE
        }else{
            moviePoster?.visibility = View.VISIBLE
            movieDesc?.visibility = View.VISIBLE
            movieDetailBottomNav.visibility = View.VISIBLE
        }
    }
}