package com.houdin.br.movies.shared.di

import com.houdin.br.movies.shared.NetworkConstants
import com.houdin.br.movies.shared.network.MovieApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Matheus Gomes on 09/05/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor = MovieApiInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
}
