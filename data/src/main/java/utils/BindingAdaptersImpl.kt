package utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class BindingAdaptersImpl @Inject constructor() : BindingAdapters {
    override fun ImageView.setImageUrl(url: String?){
        Glide.with(this).load(url).into(this)
    }
}