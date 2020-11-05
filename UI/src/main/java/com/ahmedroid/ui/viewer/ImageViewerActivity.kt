package com.ahmedroid.ui.viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmedroid.ui.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image_viewer.*

class ImageViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        loadFullPhoto()
    }

    private fun loadFullPhoto() {
              Glide.with(this@ImageViewerActivity)
                  .asBitmap().load(intent.extras?.get(EXTRA_FULL_IMAGE_URL)).into(activity_image_viewer_photoView)
    }

    companion object {
        const val EXTRA_FULL_IMAGE_URL = "fullImageUrl"
    }
}