//package com.toliveira.shopbuddy.shopping
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.toliveira.shopbuddy.R
//import com.toliveira.shopbuddy.databinding.FragmentShoppingBinding
//import com.toliveira.data.product.Product
//import com.toliveira.shopbuddy.model_old.Store
//import com.toliveira.shopbuddy.list.AddProductActivity
//import com.toliveira.shopbuddy.viewModel.ProductViewModel
//import com.toliveira.shopbuddy.viewModel.StoreViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import javax.inject.Inject
//
//@Suppress("KotlinConstantConditions")
//@AndroidEntryPoint
//class ShoppingFragment : Fragment() {
//
//    @Inject
//    private lateinit var shoppingViewModel: ShoppingViewModel
//    private lateinit var binding: FragmentShoppingBinding
//
//
//
//    private lateinit var mStoreViewModel: StoreViewModel
//    private lateinit var mProductViewModel: ProductViewModel
//    private var productFiltered = mutableListOf<com.toliveira.data.product.Product>()
//    private lateinit var shoppingAdapter: ShoppingAdapter
//    private lateinit var spinnerAdapter: ArrayAdapter<Store>
//    private var currentStore: Store? = null
//    private var storeCost = 0F
//    private var storeList = mutableListOf<Store>()
//    private val defaultStore = Store(-1, "Select the Store..", 0F, 0)
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentShoppingBinding.inflate(inflater, container, false)
//        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]
//        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
//
//
//
//
//
//
//        return binding.root
//    }
//
//
//    override fun onResume() {
//        binding.addProductButton.setOnClickListener {
//            val intent = Intent(context, AddProductActivity::class.java)
//            startActivity(intent)
//        }
//
//
//        mProductViewModel.getAllProducts.observe(viewLifecycleOwner, Observer { products ->
//            if (products.isNotEmpty()) {
//                mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { stores ->
//                    if (stores.isNotEmpty()) {
//                        binding.productEmptyContainer.visibility = View.GONE
//                        binding.storeEmptyContainer.visibility = View.GONE
//                        binding.recyclerList.visibility = View.VISIBLE
//                        getSelectedSpinner()
//                    } else {
//                        binding.productEmptyContainer.visibility = View.GONE
//                        binding.storeEmptyContainer.visibility = View.VISIBLE
//                        binding.recyclerList.visibility = View.GONE
//                    }
//                })
//            } else {
//                binding.productEmptyContainer.visibility = View.VISIBLE
//                binding.storeEmptyContainer.visibility = View.GONE
//                binding.recyclerList.visibility = View.GONE
//            }
//        })
//
//        setSpinner()
//        binding.storeSpinner.adapter = spinnerAdapter
//
//
//
//        super.onResume()
//    }
//
//    private fun setSpinner() {
//        spinnerAdapter =
//            ArrayAdapter(requireContext(), R.layout.custom_spinner, ArrayList<Store>())
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerAdapter.clear()
//        spinnerAdapter.add(defaultStore)
//        var count = 0
//
//        mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { storeName ->
//            storeList.clear()
//            storeName.forEach {
//                storeList.add(it)
//            }
//            if (storeList.size != count) {
//                storeList.forEach {
//                    spinnerAdapter.add(it)
//                    count++
//                }
//            }
//        })
//
//    }
//
//    private fun getSelectedSpinner() {
//        binding.storeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//
//            override fun onItemSelected(
//                parent: AdapterView<*>?, view: View?, position: Int, id: Long
//            ) {
//                var currentStoreName: String? = binding.storeSpinner.selectedItem.toString()
//                if (currentStoreName != null && currentStoreName != "Select the Store..") {
//                    currentStore = getCurrentStore(currentStoreName)
//                    val newAdapter = ShoppingAdapter(context, currentStore, mProductViewModel)
//                    mProductViewModel.getAllProducts.observe(
//                        viewLifecycleOwner,
//                        Observer { productList ->
//                            storeCost = 0F
//                            productFiltered.clear()
//                            productList.forEach {
//                                if (it.productStoreId == null || it.productStoreId == currentStore?.storeId) {
//                                    if (it !in productFiltered) {
//                                        productFiltered.add(it)
//                                    }
//                                }
//                            }
//                        })
//                    binding.totalText.text = String.format("%.2f €", currentStore?.storeSpending)
//                    binding.recyclerList.adapter = newAdapter
//                    binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
//                    newAdapter.setData(productFiltered)
//                }
//
//                if (currentStoreName == "Select the Store..") {
//                    val newAdapter = ShoppingAdapter(context, defaultStore, mProductViewModel)
//                    binding.totalText.text = "0.00 €"
//                    binding.recyclerList.adapter = newAdapter
//                    binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
//                    mProductViewModel.getAllProducts.observe(
//                        viewLifecycleOwner,
//                        Observer { productList ->
//                            newAdapter.setData(productList)
//                        })
//
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                mProductViewModel.getAllProducts.observe(
//                    viewLifecycleOwner,
//                    Observer { productList ->
//                        shoppingAdapter.setData(productList)
//                    })
//
//            }
//        }
//
//
//    }
//
//    private fun getCurrentStore(storeName: String?): Store? {
//        return storeList.find { it.storeName == storeName }
//    }
//}