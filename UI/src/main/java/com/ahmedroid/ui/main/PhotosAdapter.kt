package com.ahmedroid.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedroid.ui.databinding.PhotoItemGridBinding
import com.ahmedroid.ui.databinding.PhotoItemListBinding
import entities.Photo

class PhotosAdapter(
    private val layoutManager: GridLayoutManager,
    private val onItemClickListener: (view: ImageView, pos: Int) -> Unit,
    private val onLoadMoreListener: (pos: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photos: List<Photo> = listOf()

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
            is GridViewHolder -> holder.bind(photos[holder.layoutPosition])
            is ListViewHolder -> holder.bind(photos[holder.layoutPosition])
        }
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount > 1) ViewType.GRID.ordinal
        else ViewType.LIST.ordinal
    }

    fun addItems(photoItems: List<Photo>) {
        photos = photoItems
        notifyDataSetChanged()
    }

    inner class GridViewHolder(val binding: PhotoItemGridBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            this.binding.root.setOnClickListener {
                onItemClickListener.invoke(binding.photoItemImageView, layoutPosition)
            }
            onLoadMoreListener(adapterPosition)
            binding.photo = item
            binding.executePendingBindings()
        }
    }

    inner class ListViewHolder(val binding: PhotoItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            this.binding.root.setOnClickListener {
                onItemClickListener.invoke(binding.photoItemImageView, layoutPosition)
            }
            onLoadMoreListener(adapterPosition)
            binding.photo = item
            binding.executePendingBindings()
        }
    }
}