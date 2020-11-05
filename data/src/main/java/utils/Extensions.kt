package utils

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import kotlinx.coroutines.*

const val SEARCH_DEBOUNCE = 500L

inline fun EditText.onDebounceQueryTextChange(
    scope: CoroutineScope = GlobalScope,
    crossinline callback: (String) -> Unit
) {
    var searchFor: String? = ""
    doOnTextChanged { text, start, before, count ->
        val searchText = text?.toString()?.trim()
        searchFor = searchText.toString()

        scope.launch {
            delay(SEARCH_DEBOUNCE)  //debounce timeOut
            if (searchText?.equals(searchFor, true) != true) {
                return@launch
            }

                callback(searchText.toString())
        }
    }
}
