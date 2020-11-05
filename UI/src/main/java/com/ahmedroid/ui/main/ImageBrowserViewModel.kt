package com.ahmedroid.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.scopes.ActivityScoped
import entities.Photo
import network.Resource
import repos.PhotosRepo
import utils.NetworkHelper
import java.lang.Error
import javax.inject.Inject

class ImageBrowserViewModel @ViewModelInject constructor(
    private val photosRepo: PhotosRepo,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    var pageNumber = 0
    var photosList: List<Photo> = listOf()

    fun loadPhotos() = liveData {
        emit(Resource.Loading(show = true))

        emit(fetchPhotos())

        incrementPageNumber()

        emit(Resource.Loading(show = false))
    }

    private suspend fun fetchPhotos(): Resource<List<Photo>> {
        return if (networkHelper.isNetworkConnected()) {
            val photosResult = photosRepo.getPhotosWithPage(pageNumber)
            if (photosResult is Resource.Success<List<Photo>>) {
                photosList = photosResult.data ?: listOf()
            }
            photosResult
        } else {
            Resource.Error(0, "No Internet Connection")
        }
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