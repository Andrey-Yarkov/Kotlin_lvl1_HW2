package com.example.hw2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.R
import com.example.hw2.classes.ImagePlate

class ImageListRVAdapter(
    private val images: MutableList<ImagePlate>
) : RecyclerView.Adapter<ImageListRVAdapter.RvViewHolder>() {

    // Describes an item view and its metadata inside the recycler view
    class RvViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById(R.id.image_plate)

        // Bind item view with object
        fun bind(image : ImagePlate) {

        }
    }

    fun addImage(image: ImagePlate) {
        images.add(image)
        notifyItemInserted(images.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder { // Called when RV needs a new view holder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_plate, null) // Connect view item with xml file
        return RvViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) { // Called by RV to display data on specific position
        holder.bind(images[position])
    }

    companion object {

    }

}