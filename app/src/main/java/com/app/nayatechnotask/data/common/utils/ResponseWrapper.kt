package com.app.nayatechnotask.data.common.utils

import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    @SerializedName("status") var status: String? = null,
    @SerializedName("results") var data : List<T>? = null
)


data class WrappedResponse<T> (
    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: T? = null
)