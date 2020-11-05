package entities

import com.google.gson.annotations.SerializedName

data class SearchWrapper(
    @SerializedName(value = "total")
    val total: Int? = null,

    @SerializedName(value = "results")
    val results: List<Photo>? = null
)