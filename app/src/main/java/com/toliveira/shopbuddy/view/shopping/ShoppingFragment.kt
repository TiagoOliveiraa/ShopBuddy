package com.toliveira.shopbuddy.view.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

@Suppress("KotlinConstantConditions")
class ShoppingFragment : Fragment() {

    private lateinit var binding: FragmentShoppingBinding
    private lateinit var mStoreViewModel: StoreViewModel
    private lateinit var mProductViewModel: ProductViewModel
    private var productFiltered = mutableListOf<Product>()
    private lateinit var shoppingAdapter: ShoppingAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<Store>
    private var currentStore: Store? = null
    private var storeCost = 0F
    private var storeList = mutableListOf<Store>()
    private val defaultStore = Store(-1, "Select the Store..", 0F)
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
        setSpinner()
        getSelectedSpinner()




        binding.storeSpinner.adapter = spinnerAdapter






        return binding.root
    }

    private fun setSpinner() {
        spinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.custom_spinner, ArrayList<Store>())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAdapter.clear()
        spinnerAdapter.add(defaultStore)

        mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { storeName ->
            storeName.forEach {
                spinnerAdapter.add(it)
                if (it !in storeList) {
                    storeList.add(it)
                }
            }
        })

    }

    private fun getSelectedSpinner() {
        binding.storeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                var currentStoreName: String? = binding.storeSpinner.selectedItem.toString()
                if (currentStoreName != null && currentStoreName != "Select the Store..") {
                    currentStore = getCurrentStore(currentStoreName)
                    val newAdapter = ShoppingAdapter(context, currentStore, mProductViewModel)
                    mProductViewModel.getAllProducts.observe(
                        viewLifecycleOwner,
                        Observer { productList ->
                            storeCost = 0F
                            productFiltered.clear()
                            productList.forEach {
                                if (it.productStoreId == null || it.productStoreId == currentStore?.storeId) {
                                    if (it !in productFiltered) {
                                        productFiltered.add(it)
                                    }
                                    if (it.productStoreId == currentStore?.storeId) {
                                        storeCost += it.productPrice * it.productQuantity
                                    }
                                }
                            }


                        })
                    binding.totalText.text = String.format("%.2f €", storeCost)
                    binding.recyclerList.adapter = newAdapter
                    binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
                    newAdapter.setData(productFiltered)
                }

                if (currentStoreName == "Select the Store..") {
                    val newAdapter = ShoppingAdapter(context, defaultStore, mProductViewModel)
                    binding.totalText.text = "0.00 €"
                    binding.recyclerList.adapter = newAdapter
                    binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
                    mProductViewModel.getAllProducts.observe(
                        viewLifecycleOwner,
                        Observer { productList ->
                            newAdapter.setData(productList)
                        })

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


    }

    private fun getCurrentStore(storeName: String?): Store? {
        return storeList.find { it.storeName == storeName }
    }
}