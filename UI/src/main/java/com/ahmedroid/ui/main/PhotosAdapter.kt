package com.ahmedroid.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedroid.ui.R
import com.ahmedroid.ui.databinding.PhotoItemGridBinding
import com.ahmedroid.ui.databinding.PhotoItemListBinding
import entities.Photo

class PhotosAdapter(
    private val photos: List<Photo>,
    private val layoutManager: GridLayoutManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        LIST,
        GRID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ViewType.GRID.ordinal -> GridViewHolder(PhotoItemGridBinding.inflate(layoutInflater))
            else -> ListViewHolder(PhotoItemListBinding.inflate(layoutInflater))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridViewHolder -> holder.bind(photos[position])
            is ListViewHolder -> holder.bind(photos[position])
        }
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount > 1) ViewType.GRID.ordinal
        else ViewType.LIST.ordinal
    }

    inner class GridViewHolder(val binding: PhotoItemGridBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            binding.photo = item
            binding.executePendingBindings()
        }
    }

    inner class ListViewHolder(val binding: PhotoItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            binding.photo = item
            binding.executePendingBindings()
        }
    }
}