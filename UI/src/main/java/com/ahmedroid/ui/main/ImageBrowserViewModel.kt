package com.ahmedroid.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import network.Resource
import repos.PhotosRepo

class ImageBrowserViewModel @ViewModelInject constructor(
    private val photosRepo: PhotosRepo
): ViewModel() {

    fun loadPhotos() = liveData {
        emit(Resource.Loading(show = true))

        val t = photosRepo.getPhotos()
        emit(t)
        emit(Resource.Loading(show = false))
    }
}