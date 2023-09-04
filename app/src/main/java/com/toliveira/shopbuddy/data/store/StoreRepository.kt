package com.toliveira.shopbuddy.data.store

import androidx.lifecycle.LiveData
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store

data class StoreRepository(private val storeDao: StoreDao){
    val getAllStores: LiveData<List<Store>> = storeDao.getAllStores()


    fun addProduct(product: Product){
        storeDao.addProduct(product)
    }

    fun getStoreWithProducts(storeName: String){
        storeDao.getStoreWithProducts(storeName)
    }

    fun addStore(store: Store){
        storeDao.addStore(store)
    }


}