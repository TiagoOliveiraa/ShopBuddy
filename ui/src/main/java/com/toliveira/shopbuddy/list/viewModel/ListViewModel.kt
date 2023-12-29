package com.toliveira.shopbuddy.list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toliveira.domain.useCases.product.GetProductsUseCase
import com.toliveira.shopbuddy.list.factory.ListUIFactory
import com.toliveira.shopbuddy.list.model.ListUI
import com.toliveira.shopbuddy.model.ProductUI
import com.toliveira.shopbuddy.model.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val listUIFactory: ListUIFactory
) : ViewModel() {

    private val _getAllProducts: MutableStateFlow<ListUI> = MutableStateFlow(ListUI.EMPTY)
    val getAllProducts: StateFlow<ListUI> get() = _getAllProducts

    init {
        viewModelScope.launch {
            val products = listUIFactory(getProductsUseCase.invoke().map {it -> it.toUI() }.toList())
            _getAllProducts.emit(products)
        }
    }


}