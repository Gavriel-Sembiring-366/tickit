package com.example.tickit.ui.sinopsis

import FilmViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.databinding.FragmentSinopsisBinding
import com.example.tickit.entities.film.FilmRepository


class SinopsisFragment : Fragment() {

    private var _binding: FragmentSinopsisBinding? = null
    private val repository by lazy { FilmRepository(requireContext()) }
    private val filmViewModel: FilmViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinopsisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDetailViewModel: MovieDetailViewModel by activityViewModels()


        movieDetailViewModel.currentFilmId.observe(viewLifecycleOwner) { filmId ->
            filmViewModel.getFilmById(filmId)
//            Toast.makeText(requireContext(), "ID FILM SINOPSIS ${filmId}", Toast.LENGTH_SHORT).show()
        }

        filmViewModel.filmDataById.observe(viewLifecycleOwner) { film ->
//            Toast.makeText(requireContext(), "ID FILM SINOPSIS SUKSES ${film.film_id}", Toast.LENGTH_SHORT).show()
            if (film != null) {
                binding.sinopsisText.text = film.sinopsis
                binding.sutradaraText.text = film.sutradara
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
