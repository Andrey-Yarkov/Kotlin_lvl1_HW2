package com.example.hw2.internet_access_module

import com.example.hw2.user_interface.ImageListFragment.Companion.monkeyImageRequest
import retrofit2.Response
import retrofit2.http.GET

interface MonkeyImageApi {
    @GET(monkeyImageRequest)
    suspend fun image() : Response<MonkeyImage>
}