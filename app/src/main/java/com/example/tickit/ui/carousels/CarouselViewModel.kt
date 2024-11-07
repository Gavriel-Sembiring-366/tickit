package com.example.tickit.ui.carousels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tickit.R

class CarouselViewModel : ViewModel() {

    private val _listCarouselItem = MutableLiveData<MutableList<ItemCarousel>>(
        mutableListOf(
            ItemCarousel("Item 1", R.drawable.bryce_canyon),
            ItemCarousel("Item 2", R.drawable.bryce_canyon),
            ItemCarousel("Item 3", R.drawable.bryce_canyon),
            ItemCarousel("Item 4", R.drawable.bryce_canyon)
        )
    )
    val listCarouselItem: LiveData<MutableList<ItemCarousel>> = _listCarouselItem

}


