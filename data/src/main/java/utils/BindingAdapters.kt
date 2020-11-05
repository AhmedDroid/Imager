package utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

interface BindingAdapters {

    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?)
}