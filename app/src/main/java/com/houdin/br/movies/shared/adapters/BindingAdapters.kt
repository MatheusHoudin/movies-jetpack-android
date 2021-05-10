package com.houdin.br.movies.shared.adapters

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
