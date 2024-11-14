package com.example.tickit.ui.sinopsis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tickit.databinding.FragmentSinopsisBinding
import com.example.tickit.entities.film.FilmRepository

class SinopsisFragment : Fragment() {

    private var _binding: FragmentSinopsisBinding? = null
    private val repository by lazy { FilmRepository(requireContext()) }
    private val viewModel: SinopsisViewModel by viewModels { SinopsisViewModelFactory(repository) }

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSinopsisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getFilmById(1)
        viewModel.data.observe(viewLifecycleOwner) { film ->
            if (film != null) {
                binding.sinopsisText.text = film.sinopsis?: ""
                binding.sutradaraText.text = film.sutradara?: ""
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
