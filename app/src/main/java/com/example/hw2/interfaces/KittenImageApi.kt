package com.example.hw2.interfaces

import com.example.hw2.files.KittenImage
import com.example.hw2.fragments.ImageListFragment.Companion.kittenImageRequest
import retrofit2.Response
import retrofit2.http.GET

interface KittenImageApi {
    @GET(kittenImageRequest)
    suspend fun image() : Response<List<KittenImage>>
}