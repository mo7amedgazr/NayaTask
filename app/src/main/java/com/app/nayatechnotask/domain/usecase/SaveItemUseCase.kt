package com.app.nayatechnotask.domain.usecase

import com.app.nayatechnotask.data.common.response.ItemsListResponse
import com.app.nayatechnotask.data.common.utils.DataState
import com.app.nayatechnotask.data.common.utils.NetworkHelper
import com.app.nayatechnotask.domain.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveItemUseCase @Inject constructor(private val listItemsRepository: ListItemsRepository) {

    data class Params(
        val id: String
    )

    suspend fun saveItem(id: String) {
        listItemsRepository.saveToWishlist(id)
    }

    suspend fun removeItem(id: String){
        listItemsRepository.removeFromWishlist(id)
    }

}