package com.houdin.br.movies.shared.model

import com.google.gson.annotations.SerializedName

/**
 * @author Matheus Gomes on 09/05/2021.
 */
data class GenericListResponse<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
