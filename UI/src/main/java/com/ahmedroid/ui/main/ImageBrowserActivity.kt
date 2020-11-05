package com.ahmedroid.ui.main

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedroid.ui.R
import com.ahmedroid.ui.viewer.ImageViewerActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import entities.Photo
import kotlinx.android.synthetic.main.activity_image_broswer.*
import network.Resource

@AndroidEntryPoint
class ImageBrowserActivity : AppCompatActivity() {

    private val imageBrowserViewModel: ImageBrowserViewModel by viewModels()

    private var photosAdapter: PhotosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_broswer)
        setSupportActionBar(activity_image_browser_toolbar)
        loadPhotos()
        initViews()
    }

    private fun initViews() {
        activity_image_browser_layout_imageView.setOnClickListener {
            if (imageBrowserViewModel.isGrid) {
                activity_image_browser_layout_imageView
                    .setImageDrawable(
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_grid_on, application.theme)
                    )
                imageBrowserViewModel.isGrid = !imageBrowserViewModel.isGrid
                (activity_image_browser_recyclerView.layoutManager as GridLayoutManager).spanCount = 1
            } else {
                activity_image_browser_layout_imageView
                    .setImageDrawable(
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_view_list, application.theme)
                    )
                imageBrowserViewModel.isGrid = !imageBrowserViewModel.isGrid
                (activity_image_browser_recyclerView.layoutManager as GridLayoutManager).spanCount = 3
            }
            photosAdapter?.notifyItemRangeChanged(0, photosAdapter?.itemCount ?: 0)
        }

        photosAdapter = PhotosAdapter(activity_image_browser_recyclerView.layoutManager as GridLayoutManager, { view,
                                                                                                                pos ->
            val intent = Intent(this, ImageViewerActivity::class.java)
            intent.putExtra(
                ImageViewerActivity.EXTRA_FULL_IMAGE_URL,
                imageBrowserViewModel.photosList[pos].urls?.fullPhotoUrl
            )

            val options = ActivityOptions
                .makeSceneTransitionAnimation(this, view, getString(R.string.image_view_transition))

            startActivity(intent, options.toBundle())
        }, {
            if (it >= imageBrowserViewModel.photosList.size / 2) {
//                imageBrowserViewModel.loadPhotos()
            }
        })
    }

    private fun loadPhotos() {
        imageBrowserViewModel.loadPhotos().observe(this, ::handlePhotosResource)
    }

    private fun handlePhotosResource(resource: Resource<List<Photo>>) {
        when (resource) {
            is Resource.Loading -> {
                showLoading(resource.show)
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

    private fun showLoading(isShowLoading: Boolean) {
        activity_image_browser_refreshLayout.isRefreshing = isShowLoading
    }

    private fun showPhotos(photos: List<Photo>) {
        photosAdapter?.addItems(photos)
        activity_image_browser_recyclerView.adapter = photosAdapter
    }
}