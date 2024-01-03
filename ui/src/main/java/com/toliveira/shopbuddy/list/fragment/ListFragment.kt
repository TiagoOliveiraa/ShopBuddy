package com.toliveira.shopbuddy.list.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.databinding.FragmentListBinding
import com.toliveira.shopbuddy.list.AddProductActivity
import com.toliveira.shopbuddy.list.adapter.ListAdapter
import com.toliveira.shopbuddy.list.viewModel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by viewModels()
    private var adapter = ListAdapter()
    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater)

        viewLifecycleOwner.lifecycleScope.launch {
            listViewModel.getAllProducts.collect { productsList ->
                adapter.setData(productsList)
            }
        }
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerList.adapter = adapter


//        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
//        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]


        binding.listButton.setOnClickListener {
            var intent = Intent(context, AddProductActivity::class.java)
            startActivity(intent)
        }


//        setDeleteButton()
        return binding.root
    }

//    private fun setDeleteButton() {
//        binding.deleteAllButton.setOnClickListener {
//            var builder = AlertDialog.Builder(requireContext())
//            builder.setTitle("Delete")
//            builder.setMessage("Select an Option")
//            builder.setNeutralButton("Cancel") { _, _ -> }
//            builder.setPositiveButton("Delete All") { _, _ ->
//                var builderAll = AlertDialog.Builder(requireContext())
//                builderAll.setTitle("Delete All Products")
//                builderAll.setMessage("Are you sure you want to delete all Products?\nYou can't revert your action!")
//                builderAll.setNegativeButton("Cancel") { _, _ -> }
//                builderAll.setPositiveButton("Delete") { _, _ ->
//                    mProductViewModel.getAllProducts.observe(
//                        viewLifecycleOwner,
//                        Observer { listProducts ->
//                            listProducts.forEach {
//                                if (it.productStoreId != null) {
//                                    mStoreViewModel.updateStoreSpending(it.productStoreId, 0F)
//                                }
//                            }
//                        })
//                    mProductViewModel.deleteAllProducts()
//                }
//                builderAll.create().show()
//            }
//            builder.setNegativeButton("Delete Purchased") { _, _ ->
//                var builderPurchased = AlertDialog.Builder(requireContext())
//                builderPurchased.setTitle("Delete Purchased Products")
//                builderPurchased.setMessage("Are you sure you want to delete all purchased Products?\nYou can't revert your action!")
//                builderPurchased.setNegativeButton("Cancel") { _, _ -> }
//                builderPurchased.setPositiveButton("Delete") { _, _ ->
//                    mProductViewModel.deletePurchasedProducts()
//                    mStoreViewModel.getAllStores.observe(
//                        viewLifecycleOwner,
//                        Observer { listStores ->
//                            listStores.forEach {
//                                mStoreViewModel.updateStoreSpending(it.storeId, 0F)
//                            }
//                        })
//                }
//                builderPurchased.create().show()
//            }
//            builder.create().show()
//        }
//    }

}