package com.example.hw53

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.hw53.databinding.ItemImageBinding

class ImageAdapter(private val list:ArrayList<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    inner class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image:ImageModel){
                binding.imageView.load(image.largeImageURL)


        }
    }

    fun addImage(Image : ImageModel) {
        list.add(Image)
    }


}