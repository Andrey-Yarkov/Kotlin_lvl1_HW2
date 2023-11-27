package com.example.hw2.interfaces

import com.example.hw2.files.Gif
import com.example.hw2.fragments.ImageListFragment.Companion.gifApi
import retrofit2.Response
import retrofit2.http.GET

interface GifApi {
    @GET(gifApi)
    suspend fun gif() : Response<Gif>
}