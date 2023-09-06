package com.toliveira.shopbuddy.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.toliveira.shopbuddy.data.ShopDatabase
import com.toliveira.shopbuddy.data.store.StoreRepository
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreViewModel(application: Application) : AndroidViewModel(application){

    val getAllStores: LiveData<List<Store>>
    private val repository: StoreRepository

    init{
        val storeDao = ShopDatabase.getDatabase(application).storeDao()
        repository = StoreRepository(storeDao)
        getAllStores = repository.getAllStores
    }

    fun addProduct(product: Product){
        viewModelScope.launch (Dispatchers.IO){
            repository.addProduct(product)
        }
    }

    fun addStore(store: Store){
        viewModelScope.launch (Dispatchers.IO){
            repository.addStore(store)
        }
    }


}