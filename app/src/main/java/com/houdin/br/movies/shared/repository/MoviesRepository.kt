package com.houdin.br.movies.shared.repository

import com.houdin.br.movies.shared.api.DiscoverService
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Matheus Gomes on 10/05/2021.
 */
interface MoviesRepository {
    suspend fun fetchMovies(page: Int, perPage: Int): GenericListResponse<Movie>
}

class MoviesRepositoryImpl @Inject constructor(
    private val discoverService: DiscoverService
) : MoviesRepository {
    override suspend fun fetchMovies(
        page: Int,
        perPage: Int
    ): GenericListResponse<Movie> =
        withContext(Dispatchers.IO) { discoverService.getMovies(page, perPage) }
}
