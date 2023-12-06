package com.toliveira.shopbuddy.shopping.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toliveira.model.store.StoreRepository
import com.toliveira.shopbuddy.shopping.model.ShoppingUI
import com.toliveira.shopbuddy.shopping.model.ShoppingUIFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val shoppingUIFactory: ShoppingUIFactory
) : ViewModel(){

    private val _getAllStores: MutableStateFlow<ShoppingUI> = MutableStateFlow(
        ShoppingUI.EMPTY)
    val getAllStores: StateFlow<ShoppingUI> = _getAllStores

    init {
        viewModelScope.launch {
            _getAllStores.value = shoppingUIFactory.invoke(storeRepository.getAllStores())
        }
    }


}