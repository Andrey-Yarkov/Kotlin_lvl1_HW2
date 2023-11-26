package com.example.hw2.adapters

import android.content.Context
import android.content.pm.Signature
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
//import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.hw2.R
import com.example.hw2.classes.ImagePlate

class ImageListRVAdapter(
    val context : Context?,
    private val imagePlates: MutableList<ImagePlate>
) : RecyclerView.Adapter<ImageListRVAdapter.RvViewHolder>() {

    // Describes an item view and its metadata inside the recycler view
    class RvViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById(R.id.image_plate)

        // Bind item view with object
        fun bind(imagePlate : ImagePlate) {
            if (imagePlate.url != null) {
                Glide.with(imageView)
                    .load(imagePlate.url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_loading)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
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
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transition(withCrossFade())
                    .into(imageView);
                /*
                Glide.with(imageView)
                    .load(imagePlate.url)
                    .placeholder(R.drawable.image_loading)
                    .timeout(3000)
                    .error(R.drawable.image_loading)
                    //.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.thumbnail(Glide.with(itemView).load(R.drawable.image_loading))
                    .transition(withCrossFade())
                    .into(imageView);
                 */
                /*
                imageView.load(imagePlate.url) {
                    placeholder(R.drawable.image_loading)
                    error(R.drawable.image_loading)
                }
                 */
            }
            else {
                imageView.setImageResource(R.drawable.default_image)
            }
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

    }

}