package com.example.hw2.classes

import android.media.Image

data class ImagePlate(val index : Int) {
    val url : String? = "https://loremflickr.com/320/240/kitten?random=$index"
    var isLoaded : Boolean = false
}