package com.toliveira.shopbuddy.list

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.toliveira.shopbuddy.databinding.ActivityAddProductBinding
import com.toliveira.shopbuddy.list.viewModel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModels()
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddProduct.setOnClickListener {
            addProductToDB()
        }

        binding.addProductBackButton.setOnClickListener {
            finish()
        }




    }

    private fun isFieldsEmpty(): Boolean{
        return !(isEmpty(binding.productAddNameText.text.toString()))
    }

    private fun addProductToDB() {

        var productName: String

        if (isFieldsEmpty()){
            productName = binding.productAddNameText.text.toString()
            listViewModel.addNewProduct(productName)
            Toast.makeText(this, "Product successfully added to list!", Toast.LENGTH_LONG).show()
            finish()
        }
        else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }

    }
}