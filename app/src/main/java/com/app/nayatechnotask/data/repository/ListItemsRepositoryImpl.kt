package com.app.nayatechnotask.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.app.nayatechnotask.data.common.response.ItemsListResponse
import com.app.nayatechnotask.data.common.utils.DataState
import com.app.nayatechnotask.data.remote.api.ListItemsApi
import com.app.nayatechnotask.domain.ListItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListItemsRepositoryImpl @Inject constructor(
    private val context: Context,
    private val listItemsApi: ListItemsApi
) :
    ListItemsRepository {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "wishlist")

    override suspend fun getItemsList(
    ): Flow<DataState<ItemsListResponse>> =
        flow {
            val response = listItemsApi.getListItems()
            response.items?.forEach { item ->
                context.datastore.edit {
                    item.id?.let { itemId ->
                        if (it.contains(booleanPreferencesKey(itemId))) {
                            item.saved = true
                        }
                    }

                }
            }
            emit(DataState.Success(response))
        }

    override suspend fun saveToWishlist(id: String) {
        context.datastore.edit {
            it[booleanPreferencesKey(id)] = true
        }
    }

    override suspend fun removeFromWishlist(id: String) {
        context.datastore.edit {
            it.remove(booleanPreferencesKey(id))
        }
    }

}