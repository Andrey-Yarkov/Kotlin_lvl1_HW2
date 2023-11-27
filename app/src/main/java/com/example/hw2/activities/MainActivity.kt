package com.example.hw2.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hw2.R
import com.example.hw2.fragments.ImageListFragment

class MainActivity : AppCompatActivity() {

    private val imagesBoard : Fragment = ImageListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, imagesBoard)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cacheDir.delete()
        Glide.get(imagesBoard.requireContext()).clearDiskCache()
    }
}