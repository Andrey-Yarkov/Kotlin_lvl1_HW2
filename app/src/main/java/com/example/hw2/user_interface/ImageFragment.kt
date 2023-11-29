package com.example.hw2.user_interface

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.hw2.R
import com.example.hw2.image_processing_module.ImageListRVAdapter
import com.example.hw2.image_processing_module.ImagePlate
import com.example.hw2.internet_access_module.RetrofitController
import com.example.hw2.internet_access_module.ImageResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val arguments = arguments?.getString("url")

        val imageView = view.findViewById<ImageView>(R.id.image_plate)
        loadImage(imageView, arguments)

        return view
    }

    private fun loadImage(imageView : ImageView, url : String?) {
        Glide.with(imageView)
            .load(url)
            .placeholder(ImageListRVAdapter.placeHolderImage)
            .error(ImageListRVAdapter.errorImage)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    val toast = Toast.makeText(imageView.context, "Failed to load image", Toast.LENGTH_SHORT)
                    toast.show()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView);
    }

}