package com.example.hw2.files

import com.example.hw2.classes.ImagePlate
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface RequestController {
    suspend fun requestGif() : Result
}

sealed interface Result {
    data class Ok(val gif : Gif) : Result
    data class Error(val error : String) : Result
}

data class Gif (
    @SerializedName("data") var data : Data? = Data()
)

data class Data (
    @SerializedName("images") var images : Images? = Images()
)

data class Images (
    @SerializedName("original") var original : Original? = Original()
)

data class Original (
    @SerializedName("url") var url: String? = null
)

val apiRequest = mapOf(
    "base" to "https://api.giphy.com/v1/gifs/",
    "type" to "random",
    "api_key" to "0UTRbFtkMxAplrohufYco5IY74U8hOes",
    "tag" to "fail",
    "rating" to "g"
)