package com.example.hw2.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.R
import com.example.hw2.adapters.ImageListRVAdapter
import com.example.hw2.classes.ImagePlate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class ImageListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_image_list, container, false)

        var imagesPerRow : Int
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imagesPerRow = horizontalLayoutItemsNum
        } else {
            imagesPerRow = verticalLayoutItemsNum
        }

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_images)
        val rvAdapter = ImageListRVAdapter(context, imagePlates)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(context, imagesPerRow)

        val btnAddition : FloatingActionButton = view.findViewById(R.id.image_add_btn)

        val random : Random = Random

        btnAddition.setOnClickListener {
            val imagePlate = ImagePlate(random.nextInt(1000) + 1)
            rvAdapter.addImagePlate(imagePlate)
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        val imagePlates = mutableListOf<ImagePlate>()
        const val horizontalLayoutItemsNum = 3
        const val verticalLayoutItemsNum = 2
    }
}