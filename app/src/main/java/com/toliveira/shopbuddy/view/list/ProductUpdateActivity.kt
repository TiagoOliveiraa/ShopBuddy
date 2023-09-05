package com.toliveira.shopbuddy.view.list

import android.os.Build
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.databinding.ActivityProductUpdateBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.viewModel.ProductViewModel

class ProductUpdateActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityProductUpdateBinding
    private lateinit var mProductViewModel: ProductViewModel
    private val product by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("product", Product::class.java)
        } else {
            intent.getParcelableExtra("product")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductUpdateBinding.inflate(layoutInflater)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]



        binding.productUpdateText.setText(product?.productName)

        binding.buttonUpdate.setOnClickListener {
            updateProductOnDb()
        }









        setContentView(binding.root)


    }

    private fun updateProductOnDb() {
        if (!isEmpty(binding.productUpdateText.toString())) {

            val newProduct = Product(
                product!!.productId,
                binding.productUpdateText.text.toString(),
                product!!.productQuantity,
                product!!.productPrice,
                product!!.productStoreId
            )

            mProductViewModel.updateProduct(newProduct)
            Toast.makeText(this, "Product successfully updated!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
        }
    }


}