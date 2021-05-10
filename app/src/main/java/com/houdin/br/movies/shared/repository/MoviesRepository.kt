package com.houdin.br.movies.shared.repository

import com.houdin.br.movies.shared.api.DiscoverService
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import javax.inject.Inject

/**
 * @author Matheus Gomes on 10/05/2021.
 */
interface MoviesRepository {
    suspend fun fetchMoviesUseCase(page: Int, perPage: Int): GenericListResponse<Movie>
}

class MoviesRepositoryImpl @Inject constructor(
    private val discoverService: DiscoverService
) : MoviesRepository {
    override suspend fun fetchMoviesUseCase(
        page: Int,
        perPage: Int
    ): GenericListResponse<Movie> = discoverService.getMovies(page, perPage)
}
