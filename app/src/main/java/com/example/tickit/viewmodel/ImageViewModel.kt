package com.example.tickit.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tickit.model.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageViewModel : ViewModel() {

    private val _imageBitmap = MutableLiveData<Bitmap?>()
    val imageBitmap: LiveData<Bitmap?> get() = _imageBitmap

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchImage(imageName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getImageByName(imageName)
                if (response.isSuccessful) {
                    response.body()?.byteStream()?.let { inputStream ->
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        withContext(Dispatchers.Main) {
                            _imageBitmap.value = bitmap
                        }
                    } ?: run {
                        withContext(Dispatchers.Main) {
                            _errorMessage.value = "Failed to decode image"
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _errorMessage.value = "Failed to fetch image: ${response.code()}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            }
        }
    }
}
