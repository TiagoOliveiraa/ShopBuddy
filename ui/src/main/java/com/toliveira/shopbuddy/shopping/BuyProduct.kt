//package com.toliveira.shopbuddy.shopping
//
//import android.os.Build
//import android.os.Bundle
//import android.text.TextUtils.isEmpty
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import com.toliveira.shopbuddy.databinding.ActivityBuyProductBinding
//import com.toliveira.data.product.Product
//import com.toliveira.shopbuddy.model_old.Store
//import com.toliveira.shopbuddy.viewModel.ProductViewModel
//import com.toliveira.shopbuddy.viewModel.StoreViewModel
//import kotlin.math.abs
//
//class BuyProduct : AppCompatActivity() {
//
//
//    lateinit var binding: ActivityBuyProductBinding
//    lateinit var mProductViewModel: ProductViewModel
//    lateinit var mStoreViewModel: StoreViewModel
//    private val product by lazy {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra("product", com.toliveira.data.product.Product::class.java)
//        } else {
//            intent.getParcelableExtra("product")
//        }
//    }
//    private val store by lazy {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra("store", Store::class.java)
//        } else {
//            intent.getParcelableExtra("store")
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityBuyProductBinding.inflate(layoutInflater)
//        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
//        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]
//
//        binding.priceEditText.setText(product?.productPrice.toString())
//        if(product!!.productQuantity.toFloat() > 0) {
//            binding.quantitySlide.value = product!!.productQuantity.toFloat()
//        }
//        binding.addToCarButton.setOnClickListener {
//            addProductToCar()
//        }
//
//
//
//        setContentView(binding.root)
//    }
//
//    private fun addProductToCar() {
//
//        var price: Float = (binding.priceEditText.text.toString().toFloat())
//        val currentStoreSpending = store!!.storeSpending
//        val previousProductQuantity = product!!.productQuantity
//        val newProductQuantity = binding.quantitySlide.value.toInt()
//        var productAddedNum = newProductQuantity - previousProductQuantity
//        var newStoreSpending: Float? = 0.0f
//
//        if (!isEmpty(binding.priceEditText.text)) {
//            var udpatedProduct = com.toliveira.data.product.Product(
//                product!!.productId,
//                product!!.productName,
//                binding.quantitySlide.value.toInt(),
//                price,
//                store!!.storeId
//            )
//
//            newStoreSpending = if (productAddedNum > 0) {
//                currentStoreSpending + (productAddedNum * price)
//            } else
//                if (productAddedNum < 0) {
//                    currentStoreSpending - (abs(productAddedNum) * price)
//                } else {
//                    currentStoreSpending
//                }
//
//            if(newStoreSpending < 0){
//                newStoreSpending = 0f
//            }
//
//            var updatedStore = Store(store!!.storeId, store!!.storeName, newStoreSpending,store!!.storeColor)
//            mStoreViewModel.updateStore(updatedStore)
//
//
//            mProductViewModel.updateProduct(udpatedProduct)
//            Toast.makeText(
//                this,
//                "The product has been added to ${store!!.storeName}",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//            finish()
//        } else {
//            Toast.makeText(this, "You need to set a value!", Toast.LENGTH_SHORT).show()
//        }
//
//
//    }
//}