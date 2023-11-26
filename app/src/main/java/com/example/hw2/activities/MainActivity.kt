package com.example.hw2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.R
import com.example.hw2.adapters.ImageListRVAdapter
import com.example.hw2.classes.ImagePlate
import com.example.hw2.fragments.ImageListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagesBoard : Fragment = ImageListFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, imagesBoard)
            addToBackStack(null)
            commit()
        }

    }
}