package network

sealed class Resource {

    data class Success<out T>(val data: T?) : Resource()

    data class Error(val code: Int, val message: String) : Resource()

    data class Loading(val show: Boolean) : Resource()
}