package network

sealed class Resource<out T> {

    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error(val code: Int, val message: String) : Resource<Nothing>()

    data class Loading(val show: Boolean) : Resource<Nothing>()
}