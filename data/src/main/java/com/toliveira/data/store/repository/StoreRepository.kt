package com.toliveira.data.store.repository


import com.toliveira.data.store.model.StoreDTO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class StoreRepository @Inject constructor(private val storeDao: StoreDao) {

    suspend fun getAllStores(): List<StoreDTO> =
        storeDao.getAllStores().map { it ->
            it.map { it }
        }.toList().flatten()

    fun getStoreInfo(storeName: String): StoreDTO {
        return storeDao.getStoreInfo(storeName)
    }

    fun updateStore(store: StoreDTO) {
        storeDao.updateStore(store)
    }

    fun addStore(store: StoreDTO) {
        storeDao.addStore(store)
    }

    fun updateStoreSpending(storeId: Int, newValue: Float) {
        storeDao.updateStoreSpending(storeId, newValue)
    }

    fun deleteStore(store: StoreDTO) {
        storeDao.deleteStore(store)
    }


}