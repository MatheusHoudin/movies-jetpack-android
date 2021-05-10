package com.houdin.br.movies.shared.network

import com.houdin.br.movies.shared.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Matheus Gomes on 10/05/2021.
 */
class MovieApiInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder().apply {
            header(API_KEY, NetworkConstants.API_TOKEN)
        }.build()

        return chain.proceed(originalRequest)
    }

    private companion object {
        const val API_KEY = "Authorization"
    }
}
