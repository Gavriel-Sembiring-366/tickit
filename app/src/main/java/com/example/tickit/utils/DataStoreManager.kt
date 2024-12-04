package com.example.tickit.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tickit.model.Auth
import kotlinx.coroutines.flow.map

const val USER_DATASTORE = "user_data"

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class DataStoreManager(private val context: Context) {

    companion object {
        val AUTH_TOKEN = stringPreferencesKey("AUTH_TOKEN")
    }

    // Save the token to DataStore
    suspend fun saveToDataStore(token: String) {
        // Directly store the token as a string
        context.preferenceDataStore.edit {
            it[AUTH_TOKEN] = token
        }
    }

    // Retrieve the token from DataStore
    fun getFromDataStore() = context.preferenceDataStore.data.map {
        // Return the token wrapped inside an Auth object
        Auth(authToken = it[AUTH_TOKEN] ?: "")
    }

    // Clear the token from DataStore
    suspend fun clearDataStore() = context.preferenceDataStore.edit {
        it.clear() // Clear all stored preferences
    }
}
