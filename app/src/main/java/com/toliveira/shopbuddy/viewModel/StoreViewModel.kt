package com.toliveira.shopbuddy.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.toliveira.shopbuddy.data.ShopDatabase
import com.toliveira.shopbuddy.data.store.StoreRepository
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreViewModel(application: Application) : AndroidViewModel(application) {

    val getAllStores: LiveData<List<Store>>
    private val repository: StoreRepository

    init {
        val storeDao = ShopDatabase.getDatabase(application).storeDao()
        repository = StoreRepository(storeDao)
        getAllStores = repository.getAllStores
    }

    fun getStoreInfo(storeName: String): LiveData<Store> {
        lateinit var store : LiveData<Store>
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                store = repository.getStoreInfo(storeName)
            }
        }
        return store
    }

    fun addStore(store: Store) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStore(store)
        }
    }


}