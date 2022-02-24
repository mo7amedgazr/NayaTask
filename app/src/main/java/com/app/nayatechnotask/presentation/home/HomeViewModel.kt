package com.app.nayatechnotask.presentation.home

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.nayatechnotask.data.common.response.ItemsListResponse
import com.app.nayatechnotask.data.common.utils.DataState
import com.app.nayatechnotask.domain.usecase.ListItemsUseCase
import com.app.nayatechnotask.domain.usecase.SaveItemUseCase
import com.app.nayatechnotask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: ListItemsUseCase,
    private val saveItemUseCase: SaveItemUseCase
) : BaseViewModel() {

    private val Context.dataStore by preferencesDataStore(
        name = "wishlist"
    )

    private var _listItemsResponse: MutableLiveData<ItemsListResponse> =
        MutableLiveData()
    val listItemsResponse: LiveData<ItemsListResponse> get() = _listItemsResponse


    fun getItemsList() = viewModelScope.launch {
        showLoading.value = true
        useCase.execute(ListItemsUseCase.Params()).collect { response ->
            showLoading.value = false
            when (response) {
                is DataState.GenericError -> {
                    response.error?.errorResponse?.errorMessage?.let { err ->
                        showError.value = err
                    }
                }
                is DataState.Success -> {
                    response.value?.let { response ->
                        _listItemsResponse.value = response
                    }
                }
            }
        }
    }

    fun saveItem(id: String) = viewModelScope.launch {
        saveItemUseCase.saveItem(id)
    }

    fun removeItem(id: String) = viewModelScope.launch {
        saveItemUseCase.removeItem(id)
    }

}