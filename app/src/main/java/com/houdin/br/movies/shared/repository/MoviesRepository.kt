package com.houdin.br.movies.shared.repository

import com.houdin.br.movies.shared.api.DiscoverService
import com.houdin.br.movies.shared.dao.MovieDAO
import com.houdin.br.movies.shared.error.FailureOptions
import com.houdin.br.movies.shared.extensions.toMovie
import com.houdin.br.movies.shared.extensions.toMovieEntity
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketException
import javax.inject.Inject

/**
 * @author Matheus Gomes on 10/05/2021.
 */
interface MoviesRepository {
    suspend fun fetchMovies(page: Int, perPage: Int): ResultData<GenericListResponse<Movie>>
}

class MoviesRepositoryImpl @Inject constructor(
    private val discoverService: DiscoverService,
    private val movieDAO: MovieDAO
) : MoviesRepository {
    override suspend fun fetchMovies(
        page: Int,
        perPage: Int
    ): ResultData<GenericListResponse<Movie>> =
        withContext(Dispatchers.IO) {
            try {
                val movies = discoverService.getMovies(page, perPage)
                movieDAO.saveAll(movies.results.map { it.toMovieEntity() })
                ResultData.Success(movies)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is SocketException, is HttpException -> fetchCachedMovies()
                    else -> FailureOptions.unexpectedFailure
                }
            }
        }

    private fun fetchCachedMovies() = movieDAO.getAll().let { movies ->
        ResultData.Success(GenericListResponse(
            results = movies.map { it.toMovie() },
            page = 0,
            totalPages = 0,
            totalResults = movies.size
        ))
    }
}
