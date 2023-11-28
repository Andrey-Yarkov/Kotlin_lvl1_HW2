package com.example.hw2.image_processing_module

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.hw2.R

class ImageListRVAdapter(
    private val imagePlates: MutableList<ImagePlate>
) : RecyclerView.Adapter<ImageListRVAdapter.RvViewHolder>() {

    // Describes an item view and its metadata inside the recycler view
    inner class RvViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById(R.id.image_plate)

        // Bind item view with object
        fun bind(imagePlate : ImagePlate) {
            if (imagePlate.url != null) {
                loadImage(imagePlate)
            }
            else {
                imageView.setImageResource(R.drawable.default_image)
            }

            imageView.setOnClickListener() {
                if (imagePlate.isLoaded)
                {
                    // Open image
                }
                else {
                    loadImage(imagePlate)
                }
            }
        }

        private fun loadImage(imagePlate : ImagePlate) {
            Glide.with(imageView)
                .load(imagePlate.url)
                .placeholder(placeHolderImage)
                .error(errorImage)
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
                        imagePlate.isLoaded = true
                        return false
                    }

                })
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .transition(withCrossFade())
                .into(imageView);
        }
    }

    fun addImagePlate(imagePlate: ImagePlate) {
        imagePlates.add(imagePlate)
        notifyItemInserted(imagePlates.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder { // Called when RV needs a new view holder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_plate, null) // Connect view item with xml file
        return RvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagePlates.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) { // Called by RV to display data on specific position
        holder.bind(imagePlates[position])
    }

    companion object {
        val placeHolderImage = R.drawable.image_loading
        val errorImage = R.drawable.image_error
    }

}