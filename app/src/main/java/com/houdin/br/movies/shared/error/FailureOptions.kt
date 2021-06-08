package com.houdin.br.movies.shared.error

import com.houdin.br.movies.shared.model.ResultData

/**
 * @author Matheus Gomes on 08/06/2021.
 */
object FailureOptions {
    val noInternetConnectionFailure = ResultData.Failure(message = "No internet connection available, please try again")
    val unexpectedFailure = ResultData.Failure(message = "Sorry, an unexpected error has occurred, please try again")
}
