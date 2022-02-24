package com.app.nayatechnotask.domain.usecase

import com.app.nayatechnotask.data.common.response.ItemsListResponse
import com.app.nayatechnotask.data.common.utils.DataState
import com.app.nayatechnotask.data.common.utils.NetworkHelper
import com.app.nayatechnotask.domain.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListItemsUseCase @Inject constructor(private val listItemsRepository: ListItemsRepository) :
    NetworkHelper<ListItemsUseCase.Params, ItemsListResponse>() {

    class Params

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<ItemsListResponse>> {
        return listItemsRepository.getItemsList()
    }
}