package com.toliveira.shopbuddy.view.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.databinding.FragmentListBinding
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var mStoreViewModel: StoreViewModel
    private var productList = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater, container, false)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        val adapter =
            ListAdapter(requireContext(), mProductViewModel, mStoreViewModel, viewLifecycleOwner)
        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())


        mProductViewModel.getAllProducts.observe(viewLifecycleOwner, Observer { product ->

            if (product.isEmpty()) {
                binding.listEmptyContainer.visibility = View.VISIBLE
                binding.recyclerList.visibility = View.GONE
            } else {

                binding.listEmptyContainer.visibility = View.GONE
                binding.recyclerList.visibility = View.VISIBLE

                adapter.setData(product)

                product.forEach {
                    productList.add(it)
                }
            }
        })


        binding.listButton.setOnClickListener {
            var intent = Intent(context, AddProductActivity::class.java)
            startActivity(intent)
        }



        setDeleteButton()

        return binding.root
    }

    private fun setDeleteButton() {
        binding.deleteAllButton.setOnClickListener {
            var builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Delete")
            builder.setMessage("Select an Option")
            builder.setNeutralButton("Cancel") { _, _ -> }
            builder.setPositiveButton("Delete All") { _, _ ->
                var builderAll = AlertDialog.Builder(requireContext())
                builderAll.setTitle("Delete All Products")
                builderAll.setMessage("Are you sure you want to delete all Products?\nYou can't revert your action!")
                builderAll.setNegativeButton("Cancel") { _, _ -> }
                builderAll.setPositiveButton("Delete") { _, _ ->
                    mProductViewModel.getAllProducts.observe(
                        viewLifecycleOwner,
                        Observer { listProducts ->
                            listProducts.forEach {
                                if (it.productStoreId != null) {
                                    mStoreViewModel.updateStoreSpending(it.productStoreId, 0F)
                                }
                            }
                        })
                    mProductViewModel.deleteAllProducts()
                }
                builderAll.create().show()
            }
            builder.setNegativeButton("Delete Purchased") { _, _ ->
                var builderPurchased = AlertDialog.Builder(requireContext())
                builderPurchased.setTitle("Delete Purchased Products")
                builderPurchased.setMessage("Are you sure you want to delete all purchased Products?\nYou can't revert your action!")
                builderPurchased.setNegativeButton("Cancel") { _, _ -> }
                builderPurchased.setPositiveButton("Delete") { _, _ ->
                    mProductViewModel.deletePurchasedProducts()
                    mStoreViewModel.getAllStores.observe(
                        viewLifecycleOwner,
                        Observer { listStores ->
                            listStores.forEach {
                                mStoreViewModel.updateStoreSpending(it.storeId, 0F)
                            }
                        })
                }
                builderPurchased.create().show()
            }
            builder.create().show()
        }
    }

}