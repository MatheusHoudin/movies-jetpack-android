package com.houdin.br.movies.features.movies.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.houdin.br.movies.R
import com.houdin.br.movies.databinding.MovieItemLayoutBinding
import com.houdin.br.movies.shared.base.BaseAdapter
import com.houdin.br.movies.shared.base.BaseViewHolder
import com.houdin.br.movies.shared.model.Movie

/**
 * @author Matheus Gomes on 05/06/2021.
 */
class MoviesAdapter : BaseAdapter<Movie>() {

    lateinit var onCLick: (movie: Movie) -> Unit

    override fun getViewHolder(parent: ViewGroup) = MoviesViewHolder(
        MovieItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getLayoutRes() = R.layout.movie_item_layout

    inner class MoviesViewHolder(private val binding: MovieItemLayoutBinding) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie, position: Int) {
            binding.apply {
                setClickListener { onCLick(item) }
                movie = item
                executePendingBindings()
            }
        }
    }
}
