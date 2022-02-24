package com.app.nayatechnotask.data.remote.api

import com.app.nayatechnotask.data.common.response.ItemsListResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ListItemsApi {

    @GET("5c138271-d8dd-4112-8fb4-3adb1b7f689e")
    suspend fun getListItems(
//        @Url url: String,
    ): ItemsListResponse
}