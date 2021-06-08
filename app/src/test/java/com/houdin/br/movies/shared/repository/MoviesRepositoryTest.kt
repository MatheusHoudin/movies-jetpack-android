package com.houdin.br.movies.shared.repository

import com.houdin.br.movies.shared.api.DiscoverService
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * @author Matheus Gomes on 08/06/2021.
 */
class MoviesRepositoryTest : UnitTest() {
    private val discoverService = mockk<DiscoverService>()

    private lateinit var moviesRepository: MoviesRepository

    override fun initialize() {
        moviesRepository = MoviesRepositoryImpl(discoverService)
    }

    @Test
    fun `should call get movies with page and per page parameters`() {
        val response = GenericListResponse(
            results = emptyList<Movie>(),
            page = 1,
            totalPages = 10,
            totalResults = 20
        )
        coEvery { discoverService.getMovies(any(), any()) } returns response

        runBlocking { moviesRepository.fetchMoviesUseCase(1, 20) }

        coVerify(exactly = 1) {
            discoverService.getMovies(1, 20)
        }
        confirmVerified(discoverService)
    }
}
