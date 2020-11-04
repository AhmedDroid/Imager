package network

import com.ahmedroid.data.BuildConfig
import entities.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {
    @GET("photos")
    suspend fun getPhotosWithPage(
        @Query("page") number: Int,
        @Query("per_page") itemCount: Int = 25,
        @Query("client_id") clientID: String = BuildConfig.ACCESS_KEY,
    ): Response<List<Photo>>
}