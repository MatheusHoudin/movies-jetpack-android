package com.houdin.br.movies.shared.api

import com.houdin.br.movies.shared.model.GenericListResponse
import com.houdin.br.movies.shared.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Matheus Gomes on 09/05/2021.
 */
interface DiscoverService {

    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GenericListResponse<Movie>
}
