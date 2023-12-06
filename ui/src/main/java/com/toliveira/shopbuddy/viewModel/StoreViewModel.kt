package com.toliveira.shopbuddy.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toliveira.shopbuddy.data.store.StoreRepository
import com.toliveira.shopbuddy.model_old.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(

    private val repository: StoreRepository
) : ViewModel() {


    private val _getAllStores: MutableStateFlow<List<Store>> = MutableStateFlow(emptyList())
    val getAllStores: StateFlow<List<Store>> = _getAllStores

    init {
        _getAllStores =
    }

    fun getStoreInfo(storeName: String): LiveData<Store> {
        lateinit var store: LiveData<Store>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                store = repository.getStoreInfo(storeName)
            }
        }
        return store
    }

    fun updateStore(store: Store) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStore(store)
        }
    }

    fun addStore(store: Store) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStore(store)
        }
    }

    fun updateStoreSpending(storeId: Int, newValue: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStoreSpending(storeId, newValue)
        }
    }

    fun deleteStore(store: Store) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStore(store)
        }
    }


}