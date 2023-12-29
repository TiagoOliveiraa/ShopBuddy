package com.toliveira.shopbuddy.list.factory

import com.toliveira.shopbuddy.list.model.ListUI
import com.toliveira.shopbuddy.model.ProductUI
import javax.inject.Inject

class ListUIFactory @Inject constructor() {

    operator fun invoke(products : List<ProductUI>) : ListUI{
        return ListUI(products)
    }



}