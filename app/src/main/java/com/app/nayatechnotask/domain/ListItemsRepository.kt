package com.app.nayatechnotask.domain

import com.app.nayatechnotask.data.common.response.ItemsListResponse
import com.app.nayatechnotask.data.common.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ListItemsRepository {

    suspend fun getItemsList(): Flow<DataState<ItemsListResponse>>

    suspend fun saveToWishlist(id: String)

    suspend fun removeFromWishlist(id: String)
}