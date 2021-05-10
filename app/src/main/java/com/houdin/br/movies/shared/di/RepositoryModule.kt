package com.houdin.br.movies.shared.di

import com.houdin.br.movies.shared.repository.MoviesRepository
import com.houdin.br.movies.shared.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}
