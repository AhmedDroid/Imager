package com.ahmedroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ahmedroid.ui.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_image_broswer.*

@AndroidEntryPoint
class ImageBrowserActivity : AppCompatActivity() {

    private val imageBrowserViewModel: ImageBrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_broswer)
        setSupportActionBar(activity_image_browser_toolbar)
    }

}