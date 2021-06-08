package com.houdin.br.movies.shared.model

/**
 * @author Matheus Gomes on 10/05/2021.
 */
sealed class ResultData<out T> {
    data class Success<out T>(val data: T? = null): ResultData<T>()
    data class Loading(val nothing: Nothing? = null): ResultData<Nothing>()
    data class Failure(val message: String? = null): ResultData<Nothing>()
}
