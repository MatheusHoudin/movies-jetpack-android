package com.houdin.br.movies.shared.usecase

import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.repository.MoviesRepository
import javax.inject.Inject

/**
 * @author Matheus Gomes on 10/05/2021.
 */
interface FetchMoviesUseCase {
    suspend fun fetchMovies(page: Int, perPage: Int): ResultData<GenericListResponse<Movie>>
}

class FetchMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FetchMoviesUseCase {

    override suspend fun fetchMovies(
        page: Int,
        perPage: Int
    ): ResultData<GenericListResponse<Movie>> = moviesRepository.fetchMovies(page, perPage)
}
