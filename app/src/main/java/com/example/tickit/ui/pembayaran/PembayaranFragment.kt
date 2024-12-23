package com.example.tickit.ui.pembayaran

import FilmViewModel
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tickit.MainActivity
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.R
import com.example.tickit.model.apiService
import kotlinx.coroutines.launch

class PembayaranFragment : Fragment() {
    val filmViewModel:FilmViewModel by viewModels()
    val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pembayaran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.currentBioskopName.observe(viewLifecycleOwner) { bioskop ->
            view.findViewById<TextView>(R.id.lokasi_bioskop).text = bioskop
        }


        movieDetailViewModel.currentJadwalTanggalJam.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.waktu_film).text = it
        }

        filmViewModel.getFilmById(movieDetailViewModel.currentFilmId.value.toString())

        filmViewModel.filmDataById.observe(viewLifecycleOwner) { film ->
            view.findViewById<TextView>(R.id.nama_film_pembayaran).text = film.judul
        }

        movieDetailViewModel.currentSelectedSeatsId.observe(viewLifecycleOwner) { selected ->
            val size = selected.size
            view.findViewById<TextView>(R.id.jumlah_tiket).text = "$size Tiket"
            view.findViewById<TextView>(R.id.angkaT).text = "Rp 35,000 x $size"
            view.findViewById<TextView>(R.id.totalT).text = (size * 35000).toString()
            view.findViewById<TextView>(R.id.totalT3).text = (size * 35000).toString()
        }

        movieDetailViewModel.currentSelectedSeatsNomor.observe(viewLifecycleOwner){ selected ->
            view.findViewById<TextView>(R.id.nomor_kursi).text = selected.toString().replace("[", "").replace("]", "")
        }

        view.findViewById<Button>(R.id.bayar).setOnClickListener {
            lifecycleScope.launch {
                val selectedSeats = movieDetailViewModel.currentSelectedSeatsId.value
                if (selectedSeats.isNullOrEmpty()) {
                    return@launch
                }

                for (id in selectedSeats) {
                    try {
                        val response = apiService.deleteBangkuTersediaById(id)
                        if (response.isSuccessful) {

                            Toast.makeText(context, "Seat $id deleted successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to delete seat $id", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                    }
                }
            }
            // After the deletion operation, navigate to MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            requireActivity().finish() // Finish the current activity
        }
    }
}
