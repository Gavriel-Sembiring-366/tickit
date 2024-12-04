package com.example.tickit.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.model.Account
import com.example.tickit.model.authData
import com.example.tickit.model.apiService
import com.example.tickit.utils.DataStoreManager
import com.google.gson.Gson
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel(){

    data class LoginState(
        val isLogin : Boolean = false,
        val loading : Boolean = false,
        val data : authData? = null,
        val error : String? = null
    )

    private val _loginState = MutableLiveData(LoginState())
    val loginState: LiveData<LoginState> get() = _loginState


    data class APIError (
        val status : Int,
        val message : String,
    )

    fun loginUser(accountData: Account, dataStoreManager: DataStoreManager) {
        viewModelScope.launch {
            _loginState.value = _loginState.value?.copy(loading = true) // Update loading state

            try {
                val response = apiService.loginUser(accountData)

                if (response.code() != 200) {
                    val errorBody: APIError? = try {
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    } catch (e: Exception) {
                        null
                    }
                    throw Exception(errorBody?.message ?: "Unknown error occurred")
                }

                val responseBody = response.body() ?: throw Exception("Empty response body")
                dataStoreManager.saveToDataStore(responseBody.token)

                _loginState.value = _loginState.value!!.copy(
                    isLogin = true,
                    loading = false,
                    data = responseBody,
                    error = null
                )
            } catch (e: Exception) {
                _loginState.value = _loginState.value!!.copy(
                    loading = false,
                    error = e.message ?: "An error occurred"
                )
            }
        }
    }


}