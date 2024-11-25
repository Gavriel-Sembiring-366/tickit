package com.example.tickit.entities.film

import android.content.Context
import android.graphics.Bitmap
import com.example.tickit.database.MediaStoreHelper

class GetImgMimeName(private val context: Context) {

    fun getImgMimeLandscapeName(filmTitle: String?): Bitmap? {
        val imageMimeName = (filmTitle?.lowercase()?.replace(" ", "_") ?: "") + "_landscape.jpeg"
        return MediaStoreHelper(context).getImageFromMediaStore(imageMimeName)
    }

    fun getImgMimePotraitName(filmTitle: String?): Bitmap? {
        val imageMimeName = (filmTitle?.lowercase()?.replace(" ", "_") ?: "") + "_potrait.jpeg"
        return MediaStoreHelper(context).getImageFromMediaStore(imageMimeName)
    }
}
