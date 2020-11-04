package repos

import network.PhotosService
import network.Resource

class PhotosRepoImpl(
    private val photosService: PhotosService
) : PhotosRepo {

    override suspend fun getPhotosWithPage(number: Int): Resource {
        val response = photosService.getPhotosWithPage(number)
        return if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Error(response.raw().code(), response.message())
        }
    }
}