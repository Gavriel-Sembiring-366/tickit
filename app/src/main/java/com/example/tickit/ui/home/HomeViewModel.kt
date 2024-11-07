package com.example.tickit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tickit.R
import com.example.tickit.ui.carousels.ItemCarousel

class HomeViewModel : ViewModel() {

    private val _listHighlightMovies = MutableLiveData<MutableList<ItemCarousel>>(
        mutableListOf(
            ItemCarousel("Item 1", R.drawable.sf_coast),
            ItemCarousel("Item 2", R.drawable.sf_coast),
            ItemCarousel("Item 3", R.drawable.sf_coast),
            ItemCarousel("Item 4", R.drawable.sf_coast)
        )
    )
    val listHighlightMovies: LiveData<MutableList<ItemCarousel>> = _listHighlightMovies

    private val _listOnGoingMovies = MutableLiveData<MutableList<ItemCarousel>>(
        mutableListOf(
            ItemCarousel("Item 1", R.drawable.titanic),
            ItemCarousel("Item 2", R.drawable.titanic),
            ItemCarousel("Item 3", R.drawable.titanic),
            ItemCarousel("Item 4", R.drawable.titanic)
        )
    )
    val listOnGoingMovies: LiveData<MutableList<ItemCarousel>> = _listOnGoingMovies
}