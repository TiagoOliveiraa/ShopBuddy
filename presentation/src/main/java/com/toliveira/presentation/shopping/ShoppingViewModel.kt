package com.toliveira.presentation.shopping


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toliveira.data.store.StoreRepository
import com.toliveira.presentation.factory.ShoppingUIFactory
import com.toliveira.presentation.model.ShoppingUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val shoppingUIFactory: ShoppingUIFactory
) : ViewModel(){

    private val _getAllStores: MutableStateFlow<ShoppingUI> = MutableStateFlow(ShoppingUI.EMPTY)
    val getAllStores: StateFlow<ShoppingUI> = _getAllStores

    init {
        viewModelScope.launch {
            _getAllStores.value = shoppingUIFactory.invoke(storeRepository.getAllStores())
        }
    }


}