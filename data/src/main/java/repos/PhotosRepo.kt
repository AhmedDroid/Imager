package repos

import entities.Photo
import entities.SearchWrapper
import network.PhotosService
import network.Resource
import javax.inject.Inject

class PhotosRepo @Inject constructor(
    private val photosService: PhotosService
) {

    @Suppress("UNCHECKED_CAST")
    suspend fun getPhotosWith(searchTerm: String?, pageNumber: Int): Resource<List<Photo>> {
        val response = if (searchTerm.isNullOrBlank()) {
            photosService.getPhotosWithPage(pageNumber)
        } else {
            photosService.searchPhotoWith(searchTerm ?: "", pageNumber)
        }
        return if (response.isSuccessful) {
            if (response.body() is List<*>) {
                Resource.Success(response.body() as List<Photo>)
            } else {
                Resource.Success((response.body() as SearchWrapper).results)
            }
        } else {
            Resource.Error(response.raw().code, response.message())
        }
    }
}