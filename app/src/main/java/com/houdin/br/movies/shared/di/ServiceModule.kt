package com.houdin.br.movies.shared.di

import com.houdin.br.movies.shared.api.DiscoverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Matheus Gomes on 10/05/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService =
        retrofit.create(DiscoverService::class.java)
}
