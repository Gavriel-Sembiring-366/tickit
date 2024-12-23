package com.example.tickit.model


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("api/get-image-by-name")
    suspend fun getImageByName(@Query("name") imageName: String): Response<ResponseBody>

    @GET("api/get-film-by-status")
    suspend fun getFilmsByStatus(@Query("status") status: String): Response<FilmResponse>

    @GET("api/get-film-by-id")
    suspend fun getFilmById(@Query("filmId") filmId: String): Response<FilmResponseSingular>

    @GET("api/get-jadwal-by-film-id")
    suspend fun getJadwalByFilmId(@Query("filmId") filmId: String): Response<JadwalResponseList>

    @GET("api/bioskop/get-bioskop-by-id")
    suspend fun getBioskopById(@Query("bioskopId") bioskopId: String): Response<BioskopResponseSingular>

    @GET("api/get-bangkuTersedia-by-jadwal-id")
    suspend fun getBangkuTersediaByJadwalId(@Query("jadwalId") jadwalId: String): Response<BangkuTersediaResponseList>

    @DELETE("api/delete-bangkuTersedia-by-id")
    suspend fun deleteBangkuTersediaById(@Query("bangkuTersediaId") bangkuTersediaId: String): Response<BangkuTersediaDeleteResponse>

}