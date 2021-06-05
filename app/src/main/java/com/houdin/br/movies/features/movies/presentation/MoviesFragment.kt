package com.houdin.br.movies.features.movies.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.houdin.br.movies.databinding.MoviesLayoutBinding
import com.houdin.br.movies.features.movies.viewmodel.MoviesViewModel
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.util.EndOfScrollListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesAdapter = MoviesAdapter()
    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesLayoutBinding.inflate(inflater, container, false)
        initData(binding)
        binding.homeRvMovies.apply {
            val moviesLayoutManager = GridLayoutManager(context, 3)
            layoutManager = moviesLayoutManager
            addItemDecoration(MoviesListDecorator())
            addOnScrollListener(EndOfScrollListener(moviesLayoutManager) { initData(binding) })
            adapter = moviesAdapter
        }
        return binding.root
    }

    private fun initData(binding: MoviesLayoutBinding) {
        moviesViewModel.fetchMovies().observe(viewLifecycleOwner, {
            when (it) {
                is ResultData.Loading -> {
                    binding.isLoadingMovies = true
                }
                is ResultData.Success -> {
                    binding.isLoadingMovies = false
                    moviesAdapter.addItems(it.data!!)
                }
                is ResultData.Failure -> {

                }
                is ResultData.Exception -> {

                }
            }
        })
    }

    private inner class MoviesListDecorator : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val currentPosition = parent.getChildLayoutPosition(view)
            val restOfDivision = currentPosition % 3
            if (restOfDivision == 1) {
                outRect.apply {
                    right = 12
                    left = 12
                }
            }
        }
    }
}
