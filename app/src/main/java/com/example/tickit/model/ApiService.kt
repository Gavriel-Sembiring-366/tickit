package com.example.tickit.model


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


private val retrofit = Retrofit.Builder()
    .baseUrl("https://tickit-server-fy69.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @POST("api/auth/login")
    suspend fun loginUser(@Body account: Account?): Response<authData>

    @POST("api/auth/register")
    suspend fun registerUser(@Body account: RegisterData?): Response<authData>

    @GET("api/profile")
    suspend fun getProfile(@Header("Authorization") token: String) : Response<ProfileResponse>

}