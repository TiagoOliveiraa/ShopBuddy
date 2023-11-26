package com.toliveira.presentation.model

import com.toliveira.data.store.Store

data class ShoppingUI(
    val stores : List<Store>
){

    companion object {
        val EMPTY = ShoppingUI(
            stores = emptyList()
        )
    }

}
