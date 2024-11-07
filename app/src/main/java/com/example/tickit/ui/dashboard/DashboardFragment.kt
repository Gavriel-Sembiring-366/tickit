package com.example.tickit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tickit.R
import com.example.tickit.databinding.FragmentDashboardBinding
import com.example.tickit.entities.film.FilmRepository

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val repository by lazy { FilmRepository(requireContext()) }
    private val viewModel: DashboardViewModel by viewModels { DashboardViewModelFactory(repository) }

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.getFilmById(1)
        viewModel.data.observe(viewLifecycleOwner) { film ->
            if (film != null) {
                binding.textDashboard.text = film.sinopsis?: ""
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
