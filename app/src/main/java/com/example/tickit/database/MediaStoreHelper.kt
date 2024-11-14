package com.example.tickit.database

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.example.tickit.R
import java.io.File
import java.io.IOException

class MediaStoreHelper(private val context: Context) {

    private val contentResolver: ContentResolver = context.contentResolver


    fun addImageToMediaStore(drawableResId: Int, displayName: String) {
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableResId)
        if (bitmap == null) {
            println("Failed to load bitmap")
            return
        }

        val contentResolver = context.contentResolver
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp") // Only works on Android 10+
        }

        // Insert the image metadata into MediaStore and get the URI
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        if (uri != null) {
            try {
                // Open an output stream and write the bitmap to it
                contentResolver.openOutputStream(uri).use { outputStream ->
                    if (outputStream != null) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.flush()
                        println("Image successfully added to MediaStore with display name: $displayName")
                    } else {
                        println("Failed to open output stream.")
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println("Error writing image to MediaStore: ${e.message}")
            }
        } else {
            println("Failed to insert image into MediaStore.")
        }
    }

    fun getImageFromMediaStore(imageName: String): Bitmap? {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(imageName)

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val imageId = it.getLong(idColumn)

                val imageUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageId
                )

                return contentResolver.openInputStream(imageUri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
            }
        }

        return null  // Return null if no image was found
    }


    fun getImagesFromMediaStore(): List<Uri> {
        val imagesList = mutableListOf<Uri>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )

        val cursor = contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri = ContentUris.withAppendedId(uri, id)
                imagesList.add(contentUri)

                // Optionally, log or use the image details
                println("Image Name: $name, Size: $size, URI: $contentUri")
            }
        } ?: run {
            println("Failed to retrieve media from MediaStore.")
        }

        return imagesList
    }
}
