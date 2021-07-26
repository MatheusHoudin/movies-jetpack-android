package com.houdin.br.movies.shared.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author Matheus Gomes on 09/05/2021.
 */
@Parcelize
data class Movie(
    val id: Int,
    @SerializedName("original_title")
    val name: String,
    @SerializedName("release_date")
    val date: String?,
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("adult")
    val isAdult: Boolean,
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Double
): Parcelable
