package com.example.hw2.interfaces

import com.example.hw2.files.MonkeyImage
import com.example.hw2.fragments.ImageListFragment.Companion.monkeyImageRequest
import retrofit2.Response
import retrofit2.http.GET

interface MonkeyImageApi {
    @GET(monkeyImageRequest)
    suspend fun image() : Response<MonkeyImage>
}