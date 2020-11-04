package com.ahmedroid.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import entities.Photo
import network.Resource
import repos.PhotosRepo

@Suppress("UNCHECKED_CAST")
class ImageBrowserViewModel @ViewModelInject constructor(
    private val photosRepo: PhotosRepo
): ViewModel() {

    var pageNumber = 0
    var photosList : List<Photo> = listOf()

    fun loadPhotos() = liveData {
        emit(Resource.Loading(show = true))

        emit(fetchPhotos())

        incrementPageNumber()

        emit(Resource.Loading(show = false))
    }

    private suspend fun fetchPhotos() : Resource {
        val photosResult = photosRepo.getPhotosWithPage(pageNumber)
        if (photosResult is Resource.Success<*>) {
            photosList = photosResult.data as? List<Photo> ?: listOf()
        }

        return photosResult
    }

    private fun incrementPageNumber() {
        pageNumber++
    }

    fun refreshPhotos() = liveData {
        emit(Resource.Loading(show = true))

        resetPageNumber()

        emit(fetchPhotos())

        incrementPageNumber()

        emit(Resource.Loading(show = false))
    }

    private fun resetPageNumber() {
        pageNumber = 0
    }
}