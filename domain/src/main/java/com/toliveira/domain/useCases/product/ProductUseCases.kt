package com.toliveira.domain.useCases.product

import com.toliveira.data.product.repository.ProductRepository
import com.toliveira.domain.model.product.Product
import com.toliveira.domain.model.product.mapper.toData
import com.toliveira.domain.model.product.mapper.toDomain
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> =
        productRepository.getAllProducts().map { it.toDomain() }.toList()
}

class AddProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.addProduct(product.toData())
    }

}

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.updateProduct(product.toData())
    }

}

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(product: Product) {
        productRepository.deleteProduct(product.toData())
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
) {
    operator fun invoke(id: Int) {
        productRepository.clearProduct(id)
    }
}

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int) {
        productRepository.getProduct(id)
    }
}





