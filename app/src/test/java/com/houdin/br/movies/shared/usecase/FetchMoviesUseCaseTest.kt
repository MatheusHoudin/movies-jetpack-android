package com.houdin.br.movies.shared.usecase

import com.houdin.br.movies.UnitTest
import com.houdin.br.movies.shared.error.FailureOptions
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.lang.Exception
import java.net.SocketException

/**
 * @author Matheus Gomes on 08/06/2021.
 */
class FetchMoviesUseCaseTest : UnitTest() {
    private val moviesRepository = mockk<MoviesRepository>()

    private lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    override fun initialize() {
        fetchMoviesUseCase = FetchMoviesUseCaseImpl(moviesRepository)
    }

    @Test
    fun `should return success with generic list response when call to fetch movies is successful`() {
        val moviesResult = GenericListResponse(
            results = emptyList<Movie>(),
            totalResults = 10,
            totalPages = 10,
            page = 1
        )
        coEvery { moviesRepository.fetchMovies(any(), any()) } returns moviesResult

        val fetchMoviesResult = runBlocking { fetchMoviesUseCase.fetchMovies(1, 10) }

        Assert.assertEquals(ResultData.Success(moviesResult), fetchMoviesResult)
        coVerify(exactly = 1) {
            moviesRepository.fetchMovies(1, 10)
        }
        confirmVerified(moviesRepository)
    }

    @Test
    fun `should return no internet connection failure when call to fetch movies fails with IOException`() {
        val ioException = mockk<IOException>()
        coEvery { moviesRepository.fetchMovies(any(), any()) } throws ioException

        val noInternetConnectionFailure = runBlocking { fetchMoviesUseCase.fetchMovies(1, 10) }

        Assert.assertEquals(FailureOptions.noInternetConnectionFailure, noInternetConnectionFailure)
        coVerify(exactly = 1) {
            moviesRepository.fetchMovies(1, 10)
        }
        confirmVerified(moviesRepository)
    }

    @Test
    fun `should return no internet connection failure when call to fetch movies fails with SocketException`() {
        val socketException = mockk<SocketException>()
        coEvery { moviesRepository.fetchMovies(any(), any()) } throws socketException

        val noInternetConnectionFailure = runBlocking { fetchMoviesUseCase.fetchMovies(1, 10) }

        Assert.assertEquals(FailureOptions.noInternetConnectionFailure, noInternetConnectionFailure)
        coVerify(exactly = 1) {
            moviesRepository.fetchMovies(1, 10)
        }
        confirmVerified(moviesRepository)
    }

    @Test
    fun `should return no unexpected failure when call to fetch movies fails with Exception`() {
        val genericException = mockk<Exception>()
        coEvery { moviesRepository.fetchMovies(any(), any()) } throws genericException

        val unexpectedFailure = runBlocking { fetchMoviesUseCase.fetchMovies(1, 10) }

        Assert.assertEquals(FailureOptions.unexpectedFailure, unexpectedFailure)
        coVerify(exactly = 1) {
            moviesRepository.fetchMovies(1, 10)
        }
        confirmVerified(moviesRepository)
    }
}
