package com.houdin.br.movies.shared.di

import android.content.Context
import androidx.room.Room
import com.houdin.br.movies.shared.dao.AppDatabase
import com.houdin.br.movies.shared.dao.MovieDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Matheus Gomes on 13/06/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): MovieDAO = appDatabase.movieDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "Movies")
            .fallbackToDestructiveMigration().build()
}
