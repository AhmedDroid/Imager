package network

import entities.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotosService {
    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>
}