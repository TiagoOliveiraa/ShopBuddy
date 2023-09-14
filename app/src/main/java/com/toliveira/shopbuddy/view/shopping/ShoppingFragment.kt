package com.toliveira.shopbuddy.view.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.FragmentShoppingBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class ShoppingFragment : Fragment() {

    private lateinit var binding: FragmentShoppingBinding
    private lateinit var mStoreViewModel: StoreViewModel
    private lateinit var mProductViewModel: ProductViewModel
    private var productFiltered = mutableListOf<Product>()
    private lateinit var shoppingAdapter: ShoppingAdapter
    private var storeCost = 0F
    var storeList = mutableListOf<Store>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)
        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]



        binding.addStoreButton.setOnClickListener {
            val intent = Intent(context, AddStore::class.java)
            startActivity(intent)
        }

        var spinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.custom_spinner, ArrayList<Store>())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        shoppingAdapter = ShoppingAdapter(context, null, mProductViewModel)
        binding.recyclerList.adapter = shoppingAdapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        mProductViewModel.getAllProducts.observe(viewLifecycleOwner, Observer { productList ->
            shoppingAdapter.setData(productList)
        })


        mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { storeName ->
            spinnerAdapter.clear()
            storeName.forEach {
                spinnerAdapter.add(it)
                if (it !in storeList) {
                    storeList.add(it)
                }
            }
        })


        binding.storeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val currentStoreName: String? = binding.storeSpinner.selectedItem.toString()
                if (currentStoreName != null) {
                    val currentStore = storeList.find { it.storeName == currentStoreName }
                    val newAdapter = ShoppingAdapter(context, currentStore, mProductViewModel)

                    mProductViewModel.getAllProducts.observe(
                        viewLifecycleOwner,
                        Observer { productList ->
                            storeCost = 0F
                            productList.forEach {
                                if (it.productStoreId == null || it.productStoreId == currentStore?.storeId) {
                                    if (it !in productFiltered) {
                                        if (it.productStoreId == currentStore?.storeId) {
                                            storeCost += it.productPrice * it.productQuantity
                                        }
                                        productFiltered.add(it)
                                    }
                                }
                            }

                        })
                    binding.totalText.text = storeCost.toString()
                    binding.recyclerList.adapter = newAdapter
                    binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
                    newAdapter.setData(productFiltered)
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                mProductViewModel.getAllProducts.observe(
                    viewLifecycleOwner,
                    Observer { productList ->
                        shoppingAdapter.setData(productList)
                    })

            }


        }

        binding.storeSpinner.adapter = spinnerAdapter






        return binding.root
    }


    override fun onResume() {

        super.onResume()
    }

}