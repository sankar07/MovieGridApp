package com.codeheros.movieapp.utils

import android.net.Uri
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.codeheros.movieapp.R
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by sankar on 2021-10-11.
 */
abstract class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("posterURL")
        fun setPosterURL(simpleDraweeView: SimpleDraweeView, url: String?) {
            simpleDraweeView.hierarchy.setPlaceholderImage(
                ContextCompat.getDrawable(simpleDraweeView.context, R.drawable.shape_movie_placeholder)
            )

            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url ?: "")).build()
            simpleDraweeView.setImageRequest(imageRequest)
        }
    }
}
