package com.toliveira.domain.useCases.product

import com.toliveira.data.product.repository.ProductRepository
import com.toliveira.domain.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> = productRepository.getAllProducts()
}

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.addProduct(product)
    }

}

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.updateProduct(product)
    }

}

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.deleteProduct(product)
    }

}

class DeleteAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke() {
        productRepository.deleteAllProducts()
    }

}

class DeletePurchasedProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke() {
        productRepository.deletePurchasedProducts()
    }

}

class ClearProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(id: Int){
        productRepository.clearProduct(id)
    }
}

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(id: Int){
        productRepository.getProduct(id)
    }
}





