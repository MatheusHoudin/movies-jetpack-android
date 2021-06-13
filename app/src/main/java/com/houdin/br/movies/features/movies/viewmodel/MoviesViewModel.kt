package com.houdin.br.movies.features.movies.viewmodel

import androidx.lifecycle.*
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.usecase.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private var currentPage = INITIAL_PAGE
    private var lastPage = INITIAL_PAGE
    private val movies = mutableListOf<Movie>()

    private val _moviesResult = MutableLiveData<ResultData<List<Movie>>>()
    val moviesResult: LiveData<ResultData<List<Movie>>>
        get() = _moviesResult

    fun fetchMovies() {
        viewModelScope.launch {
            coroutineScope {
                _moviesResult.value = ResultData.Loading()
                val nextPageMoviesCall = async { fetchMoviesUseCase.fetchMovies(currentPage, PER_PAGE) }
                val resultData = nextPageMoviesCall.await()
                if (resultData is ResultData.Success) {
                    with(resultData.data!!) {
                        movies.addAll(results)
                        currentPage++
                        lastPage = totalPages
                    }
                    _moviesResult.value = ResultData.Success<List<Movie>>(data = movies)
                } else _moviesResult.postValue(resultData as ResultData<List<Movie>>)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE = 1
        const val PER_PAGE = 15
    }
}
