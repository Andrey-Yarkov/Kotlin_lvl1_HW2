package com.example.hw2.fragments

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

class ImageListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_image_list, container, false)

        val recyclerView : RecyclerView = container!!.findViewById(R.id.rv_images)
        val rvAdapter = ImageListRVAdapter(imagePlates)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        val btnAddition : FloatingActionButton = view.findViewById(R.id.image_add_btn)

        btnAddition.setOnClickListener {
            val imagePlate = ImagePlate(null)
            rvAdapter.addImagePlate(imagePlate)
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        val imagePlates = mutableListOf<ImagePlate>()
    }
}