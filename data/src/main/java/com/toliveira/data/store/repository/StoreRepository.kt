package com.toliveira.data.store.repository


import androidx.lifecycle.LiveData
import com.toliveira.data.store.mapper.toData
import com.toliveira.data.store.mapper.toDomain
import com.toliveira.data.store.model.StoreDTO
import com.toliveira.domain.model.Store
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class StoreRepository @Inject constructor(private val storeDao: StoreDao) {

    suspend fun getAllStores(): List<Store> =
        storeDao.getAllStores().map { it ->
            it.map { it.toDomain() }
        }.toList().flatten()

    fun getStoreInfo(storeName: String): Store {
        return storeDao.getStoreInfo(storeName).toDomain()
    }

    fun updateStore(store: Store) {
        storeDao.updateStore(store.toData())
    }

    fun addStore(store: Store) {
        storeDao.addStore(store.toData())
    }

    fun updateStoreSpending(storeId: Int, newValue: Float) {
        storeDao.updateStoreSpending(storeId, newValue)
    }

    fun deleteStore(store: Store) {
        storeDao.deleteStore(store.toData())
    }


}