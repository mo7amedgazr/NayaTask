package com.app.nayatechnotask.data.common.utils
import java.lang.Exception

sealed class BaseResult <out T : Any> {
    data class Success <T: Any>(val data : T) : BaseResult<T>()
    data class Error(var exception: Exception) : BaseResult<Nothing>()

}