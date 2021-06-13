package com.houdin.br.movies.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Matheus Gomes on 13/06/2021.
 */
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val date: String,
    val image: String?,
    @ColumnInfo(name = "adult") val isAdult: Boolean,
    val overview: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double
)
