package repos

import network.Resource

interface PhotosRepo {

    suspend fun getPhotos(): Resource
}