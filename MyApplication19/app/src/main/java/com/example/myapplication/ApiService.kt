package com.example.myapplication

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File

interface ApiService {
    @GET("/api/v1/users")
    fun getAllUser(): Call<ApiResponse<List<User>>>

    @GET("/api/v1/users/{id}")
    fun getUserByID(@Path("id") id: Int): Call<ApiResponse<User>>

    @GET("/api/v1/users/forget-password")
    fun forgetPassword(@Query("email") email: String): Call<ApiResponse<String>>

}