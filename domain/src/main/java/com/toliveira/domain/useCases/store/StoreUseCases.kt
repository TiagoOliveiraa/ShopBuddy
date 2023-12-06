package com.toliveira.domain.useCases.store

import com.toliveira.data.store.repository.StoreRepository
import com.toliveira.domain.model.Store
import javax.inject.Inject

class GetAllStoresUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend operator fun invoke() : List<Store> = storeRepository.getAllStores()

}

class GetStoreInfoUseCase @Inject constructor(
    private val storeRepository: StoreRepository
){
    operator fun invoke(storeName: String) : Store = storeRepository.getStoreInfo(storeName)
}

class UpdateStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
){
    operator fun invoke(store: Store) = storeRepository.updateStore(store)
}

class AddStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
){
    operator fun invoke(store: Store) = storeRepository.addStore(store)
}

class UpdateStoreSpendingUseCase @Inject constructor(
    private val storeRepository: StoreRepository
){
    operator fun invoke(storeId: Int, newValue: Float) = storeRepository.updateStoreSpending(storeId,newValue)
}

class DeleteStoreUseCase @Inject constructor(
    private val storeRepository: StoreRepository
){
    operator fun invoke(store: Store) = storeRepository.deleteStore(store)
}