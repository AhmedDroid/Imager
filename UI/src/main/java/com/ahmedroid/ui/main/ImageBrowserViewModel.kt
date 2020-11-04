package com.ahmedroid.ui.main

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel

class ImageBrowserViewModel @ViewModelInject constructor(
    private val app: Application
): AndroidViewModel(app) {

}