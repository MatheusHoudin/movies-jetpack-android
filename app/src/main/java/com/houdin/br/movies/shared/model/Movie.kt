package com.houdin.br.movies.shared.model

import com.google.gson.annotations.SerializedName

/**
 * @author Matheus Gomes on 09/05/2021.
 */
data class Movie(
    val id: Int,
    @SerializedName("original_title")
    val name: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val image: String?
)
