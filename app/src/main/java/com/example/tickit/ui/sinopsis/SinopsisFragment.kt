package com.example.tickit.ui.sinopsis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.MovieDetailViewModelFactory
import com.example.tickit.databinding.FragmentSinopsisBinding
import com.example.tickit.entities.film.FilmRepository


class SinopsisFragment : Fragment() {

    private var _binding: FragmentSinopsisBinding? = null
    private val repository by lazy { FilmRepository(requireContext()) }
    private val viewModel: SinopsisViewModel by viewModels { SinopsisViewModelFactory(repository) }

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

        val movieDetailViewModel: MovieDetailViewModel by viewModels({ requireActivity() }) {
            MovieDetailViewModelFactory(repository)
        }

        movieDetailViewModel.currentFilmId.observe(viewLifecycleOwner) { filmId ->
            Toast.makeText(requireContext(), "ID FILM SINOPSIS $filmId", Toast.LENGTH_SHORT).show()
            viewModel.getFilmById(filmId)
        }

        viewModel.data.observe(viewLifecycleOwner) { film ->
            if (film != null) {
                binding.sinopsisText.text = film.sinopsis?: "Not Found"
                binding.sutradaraText.text = film.sutradara?: "Not Found"
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
