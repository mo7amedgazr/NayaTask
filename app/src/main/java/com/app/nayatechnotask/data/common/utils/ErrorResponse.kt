package com.app.nayatechnotask.data.common.utils

import com.google.gson.annotations.SerializedName
import java.lang.Exception

data class ErrorResponse(
    @SerializedName("response")
    val errorResponse: ErrorData? = null,
):Exception()
data class ErrorData(
    @SerializedName("errCode")
    val code: String? = null,
    @SerializedName("message")
    val errorMessage: String? = null
)
