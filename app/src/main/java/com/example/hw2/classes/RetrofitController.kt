package com.example.hw2.classes

import com.example.hw2.files.RequestController
import com.example.hw2.files.Result
import com.example.hw2.interfaces.GifApi
import retrofit2.CallAdapter.Factory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess

class RetrofitController(api : String) : RequestController {

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(api)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gifApi = retrofit.create(GifApi::class.java)

    override suspend fun requestGif() : Result {
        val response = gifApi.gif()
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.Ok(it)
            } ?: Result.Error("Empty Gif")
        } else {
            Result.Error(response.code().toString())
        }
    }
}