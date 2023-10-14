package com.toliveira.shopbuddy.view.spending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.toliveira.shopbuddy.databinding.ActivityStoreManagementBinding
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class StoreManagement : AppCompatActivity() {

    private lateinit var binding: ActivityStoreManagementBinding
    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var mStoreViewModel: StoreViewModel
    private lateinit var storeList : List<Store>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        var storeAdapter = StoreAdapter(mProductViewModel,mStoreViewModel,applicationContext)
        binding.StoresContainer.adapter = storeAdapter
        binding.StoresContainer.layoutManager = LinearLayoutManager(applicationContext)

        mStoreViewModel.getAllStores.observe(this, Observer {stores ->
            storeAdapter.setData(stores)
        })

        binding.StoreBackButton.setOnClickListener {
            finish()
        }





    }





}