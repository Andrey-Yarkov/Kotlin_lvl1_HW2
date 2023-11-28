package com.example.hw2.internet_access_module

import com.google.gson.annotations.SerializedName

interface RequestController {
    suspend fun requestKittenImage() : ImageResult
    suspend fun requestMonkeyImage() : ImageResult
}

sealed interface ImageResult {
    data class Ok(val image : Image) : ImageResult
    data class Error(val error : String) : ImageResult
}

abstract class Image () {
    abstract fun getImageUrl() : String?
}

data class KittenImage(
    @SerializedName("url") var url: String? = null
) : Image()  {
    override fun getImageUrl() : String? { return url }
}

sealed interface MonkeyImageResult {
    data class Ok(val monkeyImage : MonkeyImage) : MonkeyImageResult
    data class Error(val error : String) : MonkeyImageResult
}

data class MonkeyImage(
    @SerializedName("data") var data : MonkeyDataField? = MonkeyDataField()
) : Image()  {
    override fun getImageUrl() : String? { return data?.images?.original?.url }

    data class MonkeyDataField (
        @SerializedName("images") var images : MonkeyImagesField? = MonkeyImagesField()
    )
    data class MonkeyImagesField (
        @SerializedName("original") var original : MonkeyOriginalField? = MonkeyOriginalField()
    )
    data class MonkeyOriginalField (
        @SerializedName("url") var url: String? = null
    )
}