package com.houdin.br.movies.shared.usecase

import com.houdin.br.movies.shared.error.FailureOptions
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.repository.MoviesRepository
import java.io.IOException
import java.lang.Exception
import java.net.SocketException
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
    ): ResultData<GenericListResponse<Movie>> {
        return try {
            val result = moviesRepository.fetchMoviesUseCase(page, perPage)
            ResultData.Success(result)
        } catch (e: IOException) {
            FailureOptions.noInternetConnectionFailure
        } catch (e: SocketException) {
            FailureOptions.noInternetConnectionFailure
        } catch (e: Exception) {
            FailureOptions.unexpectedFailure
        }
    }
}
