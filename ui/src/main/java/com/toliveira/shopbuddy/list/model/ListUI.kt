package com.toliveira.shopbuddy.list.model

import com.toliveira.shopbuddy.model.ProductUI

data class ListUI(
    val productsList : List<ProductUI>
){
    companion object {
        val EMPTY = ListUI(
            productsList = emptyList()
        )
    }
}
