package com.akinayhan.travelguideapp.util.extentions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.net.toUri
import com.akinayhan.travelguideapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(url: String?){
    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
            )
            .into(this)
    }
}
