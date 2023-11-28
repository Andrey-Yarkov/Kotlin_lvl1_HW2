package com.example.hw2.internet_access_module

import com.example.hw2.user_interface.ImageListFragment.Companion.kittenImageRequest
import retrofit2.Response
import retrofit2.http.GET

interface KittenImageApi {
    @GET(kittenImageRequest)
    suspend fun image() : Response<List<KittenImage>>
}