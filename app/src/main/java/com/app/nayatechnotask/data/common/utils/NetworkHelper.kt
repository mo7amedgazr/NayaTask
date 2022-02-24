package com.app.nayatechnotask.data.common.utils

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.UnknownHostException

abstract class NetworkHelper<in P, out R>{

    suspend fun  execute(parameter: P): Flow<DataState<R>> {
        return buildUseCase(parameter).buffer().catch { e ->
            when (e) {
                is HttpException -> {
                        val code = e.code()
                    val errorResponse = convertErrorBody(e)
                    emit(DataState.GenericError(code, errorResponse))
                }
                is UnknownHostException ->{
                    val code = 500
                    emit(DataState.GenericError(code, ErrorResponse()))
                }
                else -> {
                    emit(DataState.GenericError(null, null))
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
                val error = StringBuilder()
                try {
                    val bufferedReader: BufferedReader?
                    if (throwable.response()?.errorBody() != null) {
                        bufferedReader = BufferedReader(
                            InputStreamReader(
                                throwable.response()?.errorBody()?.byteStream()
                            )
                        )
                        var eLine: String?
                        while (bufferedReader.readLine().also { eLine = it } != null) {
                            error.append(eLine)
                        }
                        bufferedReader.close()
                    }
                } catch (e: Exception) {
                    error.append(e.message)
                }
            return Gson().fromJson(error.toString(), ErrorResponse::class.java)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    abstract suspend fun buildUseCase(parameter: P): Flow<DataState<R>>
}