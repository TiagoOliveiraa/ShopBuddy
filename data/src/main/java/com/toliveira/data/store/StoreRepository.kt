package com.toliveira.data.store


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class StoreRepository @Inject constructor(private val storeDao: StoreDao) {

    suspend fun getAllStores(): List<Store> = storeDao.getAllStores().toList().flatten()

    fun getStoreInfo(storeName : String) : LiveData<Store> {
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

    fun deleteStore(store: Store){
        storeDao.deleteStore(store)
    }


}