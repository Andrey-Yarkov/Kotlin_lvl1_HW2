package com.example.hw2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw2.R
import com.example.hw2.classes.ImagePlate

class ImageListRVAdapter(
    private val imagePlates: MutableList<ImagePlate>
) : RecyclerView.Adapter<ImageListRVAdapter.RvViewHolder>() {

    // Describes an item view and its metadata inside the recycler view
    class RvViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView : ImageView = itemView.findViewById(R.id.image_plate)

        // Bind item view with object
        fun bind(imagePlate : ImagePlate) {
            Glide.with(itemView).load("https://goo.gl/gEgYUd").into(imageView);
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