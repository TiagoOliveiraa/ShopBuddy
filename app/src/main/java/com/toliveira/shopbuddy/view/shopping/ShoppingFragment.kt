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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.databinding.FragmentShoppingBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class ShoppingFragment : Fragment() {

    private lateinit var binding: FragmentShoppingBinding
    private lateinit var mStoreViewModel: StoreViewModel
    private lateinit var mProductViewModel: ProductViewModel
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
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ArrayList<Store>())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val shoppingAdapter = ShoppingAdapter()
        binding.recyclerList.adapter = shoppingAdapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())





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

                    var productFiltered = mutableListOf<Product>()
                    mProductViewModel.getAllProducts.observe(
                        viewLifecycleOwner,
                        Observer { productList ->
                            productList.forEach {
                                if (it.productStoreId == null || it.productStoreId == currentStore?.storeId) {
                                    if (it !in productFiltered) {
                                        productFiltered.add(it)
                                    }
                                }
                            }

                        })
                    shoppingAdapter.setData(productFiltered)

                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

        binding.storeSpinner.adapter = spinnerAdapter






        return binding.root
    }
}