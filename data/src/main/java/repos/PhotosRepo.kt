package repos

import entities.Photo
import network.PhotosService
import network.Resource
import javax.inject.Inject

class PhotosRepo @Inject constructor(
    private val photosService: PhotosService
) {

    suspend fun  getPhotosWithPage(number: Int): Resource<List<Photo>> {
        val response = photosService.getPhotosWithPage(number)
        return if (response.isSuccessful) {
            Resource.Success(response.body())
        } else {
            Resource.Error(response.raw().code, response.message())
        }
    }
}