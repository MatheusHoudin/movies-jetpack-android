package com.houdin.br.movies.shared.extensions

import com.houdin.br.movies.shared.model.Movie
import com.houdin.br.movies.shared.model.MovieEntity

/**
 * @author Matheus Gomes on 13/06/2021.
 */
fun MovieEntity.toMovie() = Movie(
    id = this.id,
    name = this.name,
    date = this.date,
    image = this.image,
    isAdult = this.isAdult,
    overview = this.overview,
    voteAverage = this.voteAverage
)
