package com.houdin.br.movies.shared.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.houdin.br.movies.R

/**
 * @author Matheus Gomes on 05/06/2021.
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load("${view.context.getString(R.string.api_movie_base_url)}$imageUrl")
        .transition(DrawableTransitionOptions.withCrossFade())
        .transform(RoundedCorners(20))
        .into(view)
}
