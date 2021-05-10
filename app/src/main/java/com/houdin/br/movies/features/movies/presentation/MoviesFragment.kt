package com.houdin.br.movies.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.houdin.br.movies.databinding.MoviesLayoutBinding
import com.houdin.br.movies.features.movies.viewmodel.MoviesViewModel
import com.houdin.br.movies.shared.model.ResultData
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesLayoutBinding.inflate(inflater, container, false)
        initData(binding)
        return binding.root
    }

    private fun initData(binding: MoviesLayoutBinding) {
        moviesViewModel.fetchMovies().observe(viewLifecycleOwner, {
            when(it) {
                is ResultData.Loading -> {
                    binding.isLoadingMovies = true
                }
                is ResultData.Success -> {
                    binding.isLoadingMovies = false
                }
                is ResultData.Failure -> {

                }
                is ResultData.Exception -> {

                }
            }
        })
    }
}
