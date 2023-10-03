package com.toliveira.shopbuddy.data.store

import androidx.lifecycle.LiveData
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store

data class StoreRepository(private val storeDao: StoreDao){
    val getAllStores: LiveData<List<Store>> = storeDao.getAllStores()


    fun getStoreInfo(storeName : String) : LiveData<Store>{
       return storeDao.getStoreInfo(storeName)
    }

    fun updateStore(store: Store){
        storeDao.updateStore(store)
    }

    fun addStore(store: Store){
        storeDao.addStore(store)
    }

    fun updateStoreSpending(storeId: Int, newValue: Float){
        storeDao.updateStoreSpending(storeId,newValue)
    }

    fun getStore(Id: Int) : Store{
        return storeDao.getStore(Id)
    }


}