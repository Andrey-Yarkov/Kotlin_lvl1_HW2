package com.example.hw2.internet_access_module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitController(api : String) : RequestController {

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(api)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val kittenImageApi = retrofit.create(KittenImageApi::class.java)
    private val monkeyImageApi = retrofit.create(MonkeyImageApi::class.java)

    override suspend fun requestKittenImage() : ImageResult {
        val response = kittenImageApi.image()
        return if (response.isSuccessful) {
            response.body()?.let {
                ImageResult.Ok(it[0])
            } ?: ImageResult.Error("Empty Picture")
        } else {
            ImageResult.Error(response.code().toString())
        }
    }

    override suspend fun requestMonkeyImage() : ImageResult {
        val response = monkeyImageApi.image()
        return if (response.isSuccessful) {
            response.body()?.let {
                ImageResult.Ok(it)
            } ?: ImageResult.Error("Empty Gif")
        } else {
            ImageResult.Error(response.code().toString())
        }
    }
}