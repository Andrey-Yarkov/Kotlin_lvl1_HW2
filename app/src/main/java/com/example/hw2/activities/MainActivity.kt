package com.example.hw2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw2.R
import com.example.hw2.classes.ImagePlate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        val images = mutableListOf<ImagePlate>()
    }
}