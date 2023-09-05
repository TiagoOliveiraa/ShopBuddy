package com.toliveira.shopbuddy.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.ActivityAddProductBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.viewModel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.buttonAddProduct.setOnClickListener {
            addProductToDB()
        }




    }

    private fun isFieldsEmpty(): Boolean{
        return !(isEmpty(binding.productAddNameText.text.toString()))
    }

    private fun addProductToDB() {

        var productName: String

        if (isFieldsEmpty()){
            productName = binding.productAddNameText.text.toString()
            var product = Product(0,productName,0,0F,null)
            mProductViewModel.addProduct(product)
            Toast.makeText(this, "Product successfully added to list!", Toast.LENGTH_LONG).show()
            finish()
        }
        else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }

    }
}