package com.toliveira.shopbuddy.view.list

import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.FragmentAddProductBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.viewModel.ProductViewModel


class AddProduct : Fragment() {

    private lateinit var binding: FragmentAddProductBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]


        binding.buttonAddProduct.setOnClickListener {
            addProduct()
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun isFieldsEmpty(): Boolean {

        return !TextUtils.isEmpty(binding.productAddNameText.text.toString())

    }

    private fun addProduct() {

        var productName = binding.productAddNameText.text.toString()

        var product = Product(0, productName, 0, 0F, null)

        if (!isFieldsEmpty()) {
            mProductViewModel.addProduct(product)
            Toast.makeText(requireContext(), "Product successfully added", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addProduct_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill the product name field..",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}