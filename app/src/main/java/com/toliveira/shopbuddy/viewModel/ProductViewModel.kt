package com.toliveira.shopbuddy.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.toliveira.shopbuddy.data.ShopDatabase
import com.toliveira.shopbuddy.data.product.ProductRepository
import com.toliveira.shopbuddy.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel (application: Application): AndroidViewModel(application) {

    val getAllProducts: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDao = ShopDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        getAllProducts = repository.getAllProducts
    }

    fun addProduct (product: Product) {
        viewModelScope.launch (Dispatchers.IO){
            repository.addProduct(product)
        }

    }

    fun updateProduct (product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteProduct(product)
        }
    }


}