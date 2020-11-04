package entities

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName(value = "id")
    val id: String? = null,

    @SerializedName(value = "likes")
    val likes: Int? = null,

    @SerializedName(value = "urls")
    val urls: PhotoLinks? = null,
)