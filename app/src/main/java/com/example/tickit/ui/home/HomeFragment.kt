package com.example.tickit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tickit.R
import com.example.tickit.databinding.FragmentHomeBinding
import com.example.tickit.ui.carousels.CarouselAdapter
import com.example.tickit.ui.carousels.CarouselLayoutManager

class HomeFragment : Fragment(), CarouselAdapter.OnCenteredItemChangedListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize listCarouselItem
        val listHighlightMovies = homeViewModel.listHighlightMovies.value ?: emptyList()

        // Set up first carousel adapter and recycler view
        val carouselAdapter = CarouselAdapter(ListCarouselItem = listHighlightMovies, itemWidth = 400, itemHeight = 200, backgroundColor = null)
        binding.recyclerView.adapter = carouselAdapter
        binding.recyclerView.layoutManager = CarouselLayoutManager(requireContext(), true)
        binding.recyclerView.scrollToPosition(carouselAdapter.itemCount / 2)
        carouselAdapter.attachSnapHelperWithListener(binding.recyclerView)


        val listOnGoingMovies = homeViewModel.listOnGoingMovies.value ?: emptyList()
        // Set up second carousel adapter and recycler view

        val backgroundColor2 = ContextCompat.getColor(requireContext(), R.color.carousel_background_color)
        val carouselAdapter2 = CarouselAdapter(ListCarouselItem = listOnGoingMovies, itemWidth = 183, itemHeight = 275,  backgroundColor = backgroundColor2)
        binding.recyclerView2.adapter = carouselAdapter2
        binding.recyclerView2.layoutManager = CarouselLayoutManager(requireContext(), true)
        binding.recyclerView2.scrollToPosition(carouselAdapter2.itemCount / 2)
        carouselAdapter2.attachSnapHelperWithListener(binding.recyclerView2)


        val carouselAdapter3 = CarouselAdapter(ListCarouselItem = listOnGoingMovies, itemWidth = 183, itemHeight = 275,  backgroundColor = null)
        binding.recyclerView3.adapter = carouselAdapter3
        binding.recyclerView3.layoutManager = CarouselLayoutManager(requireContext(), true)
        binding.recyclerView3.scrollToPosition(carouselAdapter3.itemCount / 2)
        carouselAdapter3.attachSnapHelperWithListener(binding.recyclerView3)
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
