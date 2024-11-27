package com.example.tickit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tickit.R
import com.example.tickit.databinding.FragmentHomeBinding
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.ui.carousels.CarouselAdapter
import com.example.tickit.ui.carousels.CarouselLayoutManager

class HomeFragment : Fragment(), CarouselAdapter.OnCenteredItemChangedListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repository by lazy { FilmRepository(requireContext()) }
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(repository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val highlightMovieId = listOf(1,2,3,4)
        homeViewModel.getHighlightMovies(highlightMovieId)
        var listHighlightMovies: List<Film> = emptyList()
        homeViewModel.highlightMovies.observe(viewLifecycleOwner) { filmList ->
            if (filmList != null) {
                listHighlightMovies = filmList
                val carouselAdapter = CarouselAdapter(
                    ListCarouselItem = listHighlightMovies,
                    itemWidth = 400,
                    itemHeight = 200,
                    backgroundColor = null,
                    context = requireContext()
                )
                binding.recyclerView.adapter = carouselAdapter
                binding.recyclerView.layoutManager = CarouselLayoutManager(requireContext(), true)
                binding.recyclerView.scrollToPosition(carouselAdapter.itemCount / 2)
                carouselAdapter.attachSnapHelperWithListener(binding.recyclerView)
            }
        }


        val onGoingMovieId = listOf(1,2)
        homeViewModel.getOnGoingMovies(onGoingMovieId)
        var listOnGoingMovies: List<Film> = emptyList()
        homeViewModel.onGoingMovies.observe(viewLifecycleOwner) { filmList ->
            if (filmList != null) {
                listOnGoingMovies = filmList

                val backgroundColor2 = ContextCompat.getColor(requireContext(), R.color.carousel_background_color)
                val carouselAdapter2 = CarouselAdapter(
                    ListCarouselItem = listOnGoingMovies,
                    itemWidth = 183,
                    itemHeight = 275,
                    backgroundColor = backgroundColor2,
                    context = requireContext()
                )
                binding.recyclerView2.adapter = carouselAdapter2
                binding.recyclerView2.layoutManager = CarouselLayoutManager(requireContext(), true)
                binding.recyclerView2.scrollToPosition(carouselAdapter2.itemCount / 2)
                carouselAdapter2.attachSnapHelperWithListener(binding.recyclerView2)


                val carouselAdapter3 = CarouselAdapter(
                    ListCarouselItem = listOnGoingMovies,
                    itemWidth = 183,
                    itemHeight = 275,
                    backgroundColor = null,
                    context = requireContext()
                )
                binding.recyclerView3.adapter = carouselAdapter3
                binding.recyclerView3.layoutManager = CarouselLayoutManager(requireContext(), true)
                binding.recyclerView3.scrollToPosition(carouselAdapter3.itemCount / 2)
                carouselAdapter3.attachSnapHelperWithListener(binding.recyclerView3)
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCenteredItemChanged(title: String) {
//        binding.titleTextView.text = title
    }
}
