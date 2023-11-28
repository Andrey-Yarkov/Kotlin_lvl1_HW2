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
import com.example.hw2.classes.ImagePlate
import com.example.hw2.classes.RetrofitController
import com.example.hw2.files.ImageResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        rvAdapter = ImageListRVAdapter(imagePlates)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(context, imagesPerRow)

        val btnKittenAddition : Button = view.findViewById(R.id.btn_left)
        val btnMonkeyAddition : Button = view.findViewById(R.id.btn_right)

        btnKittenAddition.setOnClickListener {
            val retrofitController : RetrofitController = RetrofitController(kittenImageBaseUrl)
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val imageResult = retrofitController.requestKittenImage()
                processImageResult(imageResult)
            }
        }

        btnMonkeyAddition.setOnClickListener {
            val retrofitController : RetrofitController = RetrofitController(monkeyImageBaseUrl)
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val imageResult = retrofitController.requestMonkeyImage()
                processImageResult(imageResult)
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        MainScope().launch {
            setError(e.message ?: e.toString())
        }
    }

    private suspend fun processImageResult(imageResult: ImageResult) {
        withContext(Dispatchers.Main) {
            when (imageResult) {
                is ImageResult.Ok -> { setImage(imageResult.image.getImageUrl()) }
                is ImageResult.Error -> { setError(imageResult.error) }
                }
            }
        }

    private fun setImage(url : String?) {
        if (url != null) {
            val imagePlate : ImagePlate = ImagePlate(url)
            rvAdapter.addImagePlate(imagePlate)
        } else
        {
            val toast = Toast.makeText(context, "Cannot retrieve image URL", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun setError(error : String) {
        val toast = Toast.makeText(context, error, Toast.LENGTH_SHORT)
        toast.show()
    }

    companion object {
        val imagePlates = mutableListOf<ImagePlate>()
        lateinit var rvAdapter : ImageListRVAdapter
        const val horizontalLayoutItemsNum = 3
        const val verticalLayoutItemsNum = 2
        const val kittenImageBaseUrl = "https://api.thecatapi.com"
        const val monkeyImageBaseUrl = "https://api.giphy.com"
        const val kittenImageRequest = "https://api.thecatapi.com/v1/images/search"
        const val monkeyImageRequest = "/v1/gifs/random?api_key=0UTRbFtkMxAplrohufYco5IY74U8hOes&tag=monkey&rating=g"
    }
}