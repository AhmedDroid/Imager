package repos

import network.Resource

interface PhotosRepo {

    suspend fun getPhotosWithPage(number: Int): Resource
}