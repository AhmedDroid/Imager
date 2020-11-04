package repos

import network.PhotosService
import network.Resource

class PhotosRepoImpl(
    private val photosService: PhotosService
) : PhotosRepo {

    override suspend fun getPhotos(): Resource {
        val response = photosService.getPhotos()
        return if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Error(response.raw().code(), response.message())
        }
    }
}