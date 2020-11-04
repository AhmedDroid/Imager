package entities

import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    @SerializedName(value = "full")
    val fullPhotoUrl: String? = null,

    @SerializedName(value = "thumb")
    val thumbPhotoUrl: String? = null
)