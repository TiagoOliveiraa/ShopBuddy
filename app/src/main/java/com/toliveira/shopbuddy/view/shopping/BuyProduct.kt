package com.toliveira.shopbuddy.view.shopping

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.ActivityBuyProductBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel

class BuyProduct : AppCompatActivity() {


    lateinit var binding: ActivityBuyProductBinding
    lateinit var mProductViewModel: ProductViewModel
    private val product by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("product", Product::class.java)
        } else {
            intent.getParcelableExtra("product")
        }
    }
    private val store by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("store", Store::class.java)
        } else {
            intent.getParcelableExtra("store")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyProductBinding.inflate(layoutInflater)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        binding.addToCarButton.setOnClickListener {
            addProductToCar()
        }



        setContentView(binding.root)
    }

    private fun addProductToCar() {

        var price: Float = (binding.priceEditText.text.toString().toFloat())

        if (!isEmpty(binding.priceEditText.text)) {
            var udpatedProduct = Product(
                product!!.productId,
                product!!.productName,
                binding.quantitySlide.value.toInt(),
                price,
                store!!.storeId
            )
            mProductViewModel.updateProduct(udpatedProduct)
            Toast.makeText(this, "The product has been added to ${store!!.storeName}", Toast.LENGTH_SHORT)
                .show()
            finish()
        } else {
            Toast.makeText(this, "You need to set a value!", Toast.LENGTH_SHORT).show()
        }


    }
}