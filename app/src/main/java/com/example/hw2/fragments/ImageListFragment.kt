package com.example.hw2.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.R
import com.example.hw2.adapters.ImageListRVAdapter
import com.example.hw2.classes.AnimatedImagePlate
import com.example.hw2.classes.ImagePlate
import com.example.hw2.classes.RetrofitController
import com.example.hw2.classes.StaticImagePlate
import com.example.hw2.files.Result
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        val btnImageAddition : Button = view.findViewById(R.id.image_add_btn)
        val btnGifAddition : Button = view.findViewById(R.id.gif_add_btn)

        val random : Random = Random

        btnImageAddition.setOnClickListener {
            val url = imageApi + (random.nextInt(30000) + 1).toString()
            val imagePlate : ImagePlate = StaticImagePlate(url)
            rvAdapter.addImagePlate(imagePlate)
        }

        btnGifAddition.setOnClickListener {
            val retrofitController : RetrofitController = RetrofitController(gifBaseUrl)
            //val retrofitController : RetrofitController = RetrofitController(gifBaseUrl)
            CoroutineScope(Dispatchers.IO).launch {
                val gifResult = retrofitController.requestGif()
                withContext(Dispatchers.Main) {
                    when (gifResult) {
                        is Result.Ok -> {
                            val url = gifResult.gif.data!!.images!!.original!!.url
                            val imagePlate : ImagePlate = AnimatedImagePlate(url)
                            rvAdapter.addImagePlate(imagePlate)
                        }

                        is Result.Error -> {
                            val toast = Toast.makeText(context, "Failed to load gif", Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    }
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    /*
    private suspend fun processGifResult(gifResult : Result) {
        withContext(Dispatchers.Main) {
            when (gifResult) {
                is Result.Ok -> {
                    val imagePlate = ImagePlate(random.nextInt(30000) + 1)
                    rvAdapter.addImagePlate(imagePlate)
                }

                is Result.Error -> {

                }
            }
        }
    }
    */

    companion object {
        val imagePlates = mutableListOf<ImagePlate>()
        const val horizontalLayoutItemsNum = 3
        const val verticalLayoutItemsNum = 2
        const val imageBaseUrl = "https://loremflickr.com"
        const val gifBaseUrl = "https://api.giphy.com"
        const val imageApi = "https://loremflickr.com/320/240/kitten?lock="
        const val gifApi = "/v1/gifs/random?api_key=0UTRbFtkMxAplrohufYco5IY74U8hOes&tag=fail&rating=g"
    }
}