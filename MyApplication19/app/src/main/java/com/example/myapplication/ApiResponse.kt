package com.example.myapplication

data class ApiResponse<T>(
    val status: String,
    val data: T
)
