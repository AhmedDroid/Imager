package com.ahmedroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedroid.ui.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import entities.Photo
import kotlinx.android.synthetic.main.activity_image_broswer.*
import network.Resource

@AndroidEntryPoint
class ImageBrowserActivity : AppCompatActivity() {

    private val imageBrowserViewModel: ImageBrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_broswer)
        setSupportActionBar(activity_image_browser_toolbar)
        loadPhotos()
    }

    private fun loadPhotos() {
        imageBrowserViewModel.loadPhotos().observe(this, ::handlePhotosResource)
    }

    private fun handlePhotosResource(resource: Resource<List<Photo>>) {
        when (resource) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success<List<Photo>> -> {
                showPhotos(resource.data ?: listOf())
            }
            is Resource.Error -> {
                showError(resource.message)
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
    }

    private fun showLoading() {

    }

    private fun showPhotos(photos: List<Photo>) {
        activity_image_browser_recyclerView.adapter =
            PhotosAdapter(photos, activity_image_browser_recyclerView.layoutManager as GridLayoutManager)
    }
}