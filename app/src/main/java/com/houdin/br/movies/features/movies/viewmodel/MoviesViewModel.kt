package com.houdin.br.movies.features.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.usecase.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    fun fetchMovies() = liveData {
        emit(ResultData.Loading())
        emit(fetchMoviesUseCase.fetchMovies(1, 15))
    }
}
