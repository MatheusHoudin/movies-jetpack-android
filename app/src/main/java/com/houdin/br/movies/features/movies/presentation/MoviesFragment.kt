package com.houdin.br.movies.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.houdin.br.movies.R
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
            val moviesLayoutManager = GridLayoutManager(context, MOVIES_COLUMN_NUMBER)
            layoutManager = moviesLayoutManager
            addItemDecoration(MoviesListDecorator())
            addOnScrollListener(EndOfScrollListener(moviesLayoutManager) { initData(binding) })
            adapter = moviesAdapter.apply {
                onCLick = { movie ->
                    MoviesFragmentDirections.actionMoviesToMovieDetails(movie).let {
                        findNavController().navigate(it)
                    }
                }
            }
        }
        return binding.root
    }

    private fun initData(binding: MoviesLayoutBinding) {
        with(moviesViewModel) {
            fetchMovies()
            moviesResult.observe(viewLifecycleOwner, {
                when (it) {
                    is ResultData.Loading -> {
                        binding.isLoadingMovies = true
                    }
                    is ResultData.Success -> {
                        binding.isLoadingMovies = false
                        moviesAdapter.addItems(it.data!!)
                    }
                    is ResultData.Failure -> {
                        binding.isLoadingMovies = false
                        Toast.makeText(context, it.message!!, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private companion object {
        const val MOVIES_COLUMN_NUMBER = 3
    }
}
