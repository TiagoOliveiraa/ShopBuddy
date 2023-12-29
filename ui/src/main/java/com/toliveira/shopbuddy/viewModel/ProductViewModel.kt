//package com.toliveira.shopbuddy.viewModel
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.toliveira.shopbuddy.data.ShopDatabase
//import com.toliveira.data.product.ProductRepository
//import com.toliveira.data.product.Product
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class ProductViewModel (application: Application): AndroidViewModel(application) {
//
//    val getAllProducts: LiveData<List<com.toliveira.data.product.Product>>
//    private val repository: com.toliveira.data.product.ProductRepository
//
//    init {
//        val productDao = ShopDatabase.getDatabase(application).productDao()
//        repository = com.toliveira.data.product.ProductRepository(productDao)
//        getAllProducts = repository.getAllProducts
//    }
//
//    fun addProduct (product: com.toliveira.data.product.Product) {
//        viewModelScope.launch (Dispatchers.IO){
//            repository.addProduct(product)
//        }
//
//    }
//
//    fun updateProduct (product: com.toliveira.data.product.Product){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateProduct(product)
//        }
//    }
//
//    fun deleteProduct(product: com.toliveira.data.product.Product){
//        viewModelScope.launch (Dispatchers.IO){
//            repository.deleteProduct(product)
//        }
//    }
//
//    fun deleteAllProducts(){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteAllProducts()
//        }
//    }
//
//    fun deletePurchasedProducts(){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deletePurchasedProducts()
//        }
//    }
//
//    fun clearProduct(id: Int){
//        viewModelScope.launch (Dispatchers.IO){
//            repository.clearProduct(id)
//        }
//    }
//
//
//
//}