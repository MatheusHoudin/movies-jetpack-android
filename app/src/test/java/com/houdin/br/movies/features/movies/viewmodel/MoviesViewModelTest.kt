package com.houdin.br.movies.features.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.houdin.br.movies.UnitTest
import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.ResultData
import com.houdin.br.movies.shared.usecase.FetchMoviesUseCase
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * @author Matheus Gomes on 08/06/2021.
 */
@ExperimentalCoroutinesApi
class MoviesViewModelTest : UnitTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val fetchMoviesUseCase = mockk<FetchMoviesUseCase>()
    private lateinit var viewModel: MoviesViewModel

    private val movies = mutableListOf(
        Movie(1, "name", "date", "image"),
        Movie(2, "name2", "date2", "image2"),
        Movie(3, "name3", "date3", "image3"),
        Movie(4, "name4", "date4", "image4")
    )
    private val genericMovieResponse = GenericListResponse(
        results = movies,
        page = 1,
        totalPages = 10,
        totalResults = 4
    )

    @ObsoleteCoroutinesApi
    override fun initialize() {
        viewModel = MoviesViewModel(fetchMoviesUseCase)
    }

    @Test
    fun `should emit success when call to fetch movies is successful`() = runBlockingTest {
        val observer = mockk<Observer<ResultData<List<Movie>>>>()
        val slot = slot<ResultData<List<Movie>>>()
        val executions = mutableListOf<ResultData<List<Movie>>>()
        val expectedExecutions = mutableListOf(
            ResultData.Loading(),
            ResultData.Success(
                data = movies
            )
        )
        coEvery {
            fetchMoviesUseCase.fetchMovies(
                any(),
                any()
            )
        } returns ResultData.Success(data = genericMovieResponse)
        every { observer.onChanged(capture(slot)) } answers {
            executions.add(slot.captured)
        }
        viewModel.moviesResult.observeForever(observer)

        viewModel.fetchMovies()

        Assert.assertEquals(expectedExecutions, executions)
        coVerify(exactly = 1) {
            fetchMoviesUseCase.fetchMovies(1, 15)
        }
        confirmVerified(fetchMoviesUseCase)
    }

    @Test
    fun `should emit success twice with the two movies result`() = runBlockingTest {
        val observer = mockk<Observer<ResultData<List<Movie>>>>()
        val slot = slot<ResultData<List<Movie>>>()
        val executions = mutableListOf<ResultData<List<Movie>>>()
        val finalMoviesResult = mutableListOf(
            Movie(1, "name", "date", "image"),
            Movie(2, "name2", "date2", "image2"),
            Movie(3, "name3", "date3", "image3"),
            Movie(4, "name4", "date4", "image4"),
            Movie(1, "name", "date", "image"),
            Movie(2, "name2", "date2", "image2"),
            Movie(3, "name3", "date3", "image3"),
            Movie(4, "name4", "date4", "image4")
        )
        val expectedExecutions = mutableListOf(
            ResultData.Loading(),
            ResultData.Success(
                data = movies
            ),
            ResultData.Loading(),
            ResultData.Success(
                data = finalMoviesResult
            )
        )
        coEvery {
            fetchMoviesUseCase.fetchMovies(
                any(),
                any()
            )
        } returns ResultData.Success(data = genericMovieResponse)
        every { observer.onChanged(capture(slot)) } answers {
            executions.add(slot.captured)
        }
        viewModel.moviesResult.observeForever(observer)

        with(viewModel) {
            fetchMovies()
            fetchMovies()
        }

        Assert.assertEquals(expectedExecutions, executions)
        coVerify(exactly = 1) {
            fetchMoviesUseCase.fetchMovies(1, 15)
            fetchMoviesUseCase.fetchMovies(2, 15)
        }
        confirmVerified(fetchMoviesUseCase)
    }

    @Test
    fun `should emit failure when call to fetch movies fails`() = runBlockingTest {
        val observer = mockk<Observer<ResultData<List<Movie>>>>()
        val slot = slot<ResultData<List<Movie>>>()
        val executions = mutableListOf<ResultData<List<Movie>>>()
        val expectedExecutions = mutableListOf(
            ResultData.Loading(),
            ResultData.Failure(message = "error")
        )
        coEvery {
            fetchMoviesUseCase.fetchMovies(
                any(),
                any()
            )
        } returns ResultData.Failure(message = "error")
        every { observer.onChanged(capture(slot)) } answers {
            executions.add(slot.captured)
        }
        viewModel.moviesResult.observeForever(observer)

        viewModel.fetchMovies()

        Assert.assertEquals(expectedExecutions, executions)
        coVerify(exactly = 1) {
            fetchMoviesUseCase.fetchMovies(1, 15)
        }
        confirmVerified(fetchMoviesUseCase)
    }
}
