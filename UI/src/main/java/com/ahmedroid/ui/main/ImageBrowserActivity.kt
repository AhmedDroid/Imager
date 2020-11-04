package com.ahmedroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    }

    private fun loadPhotos() {
        imageBrowserViewModel.loadPhotos().observe(this, ::handlePhotosResource)
    }

    @Suppress("UNCHECKED_CAST")
    private fun handlePhotosResource(resource: Resource) {
        when (resource) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success<*> -> {
               showPhotos(resource.data as? List<Photo>)
            }
            is Resource.Error -> {
                showError(resource.message)
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_SHORT)
    }

    private fun showLoading() {

    }

    private fun showPhotos(photos: List<Photo>?) {

    }

}