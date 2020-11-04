package com.ahmedroid.ui.viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmedroid.ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
    }
}