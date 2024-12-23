package com.example.tickit.ui.home

import FilmViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tickit.MovieDetailViewModel
import com.example.tickit.R
import com.example.tickit.databinding.FragmentHomeBinding
import com.example.tickit.entities.film.Film
import com.example.tickit.entities.film.FilmRepository
import com.example.tickit.ui.carousels.CarouselAdapter
import com.example.tickit.ui.carousels.CarouselLayoutManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val repository by lazy { FilmRepository(requireContext()) }
    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory(repository) }
    private val filmViewModel: FilmViewModel by viewModels()

//    private val loginViewModel: LoginViewModel by viewModels()
    private var hasNavigatedToLogin = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUI()
    }

    private fun loadUI(){
        filmViewModel.fetchOngoingFilms()


        filmViewModel.ongoingFilms.observe(viewLifecycleOwner) { filmList ->
            if (!filmList.isNullOrEmpty()) {
                val ongoingAdapter = CarouselAdapter(
                    ListCarouselItem = filmList,
                    itemWidth = 400,
                    itemHeight = 200,
                    backgroundColor = null,
                    context = requireContext()
                )

                binding.recyclerView.apply {
                    adapter = ongoingAdapter
                    layoutManager = CarouselLayoutManager(requireContext(), true)
                    scrollToPosition(ongoingAdapter.itemCount / 2)
                }

                ongoingAdapter.attachSnapHelperWithListener(binding.recyclerView)
            } else {
//                Toast.makeText(requireContext(), "No ongoing films available.", Toast.LENGTH_SHORT).show()
            }
        }
        filmViewModel.fetchHighlightFilms()

        filmViewModel.highlightFilms.observe(viewLifecycleOwner) { filmList ->
            if (!filmList.isNullOrEmpty()) {
                val highlightAdapter = CarouselAdapter(
                    ListCarouselItem = filmList,
                    itemWidth = 183,
                    itemHeight = 275,
                    backgroundColor = ContextCompat.getColor(requireContext(), R.color.carousel_background_color),
                    context = requireContext()
                )

                binding.recyclerView2.apply {
                    adapter = highlightAdapter
                    layoutManager = CarouselLayoutManager(requireContext(), true)
                    scrollToPosition(highlightAdapter.itemCount / 2)
                }

                highlightAdapter.attachSnapHelperWithListener(binding.recyclerView2)
            } else {
//                Toast.makeText(requireContext(), "No highlight films available.", Toast.LENGTH_SHORT).show()
            }
        }
        filmViewModel.fetchComingSoonFilms()
        filmViewModel.comingSoonFilms.observe(viewLifecycleOwner) { filmList ->
            if (!filmList.isNullOrEmpty()) {
                val comingSoonAdapter = CarouselAdapter(
                    ListCarouselItem = filmList,
                    itemWidth = 183,
                    itemHeight = 275,
                    backgroundColor = null,
                    context = requireContext()
                )

                binding.recyclerView3.apply {
                    adapter = comingSoonAdapter
                    layoutManager = CarouselLayoutManager(requireContext(), true)
                    scrollToPosition(comingSoonAdapter.itemCount / 2)
                }

                comingSoonAdapter.attachSnapHelperWithListener(binding.recyclerView3)
            } else {
//                Toast.makeText(requireContext(), "No coming soon films available.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
