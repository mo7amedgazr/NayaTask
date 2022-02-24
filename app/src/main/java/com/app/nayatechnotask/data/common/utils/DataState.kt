package com.app.nayatechnotask.data.common.utils


sealed class DataState<out T> {
    data class Success<out T>(val value: T?) : DataState<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) : DataState<Nothing>()
//    object Loading : DataState<Nothing>()
}