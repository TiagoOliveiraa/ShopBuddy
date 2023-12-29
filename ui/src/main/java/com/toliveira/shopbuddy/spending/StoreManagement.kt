//package com.toliveira.shopbuddy.spending
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.toliveira.shopbuddy.databinding.ActivityStoreManagementBinding
//import com.toliveira.shopbuddy.model_old.Store
//import com.toliveira.shopbuddy.viewModel.ProductViewModel
//import com.toliveira.shopbuddy.viewModel.StoreViewModel
//
//class StoreManagement : AppCompatActivity() {
//
//    private lateinit var binding: ActivityStoreManagementBinding
//    private lateinit var mProductViewModel: ProductViewModel
//    private lateinit var mStoreViewModel: StoreViewModel
//    private lateinit var storeList : List<Store>
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityStoreManagementBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
//        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]
//
//        var storeAdapter = StoreAdapter(mProductViewModel,mStoreViewModel,this)
//        binding.StoresContainer.adapter = storeAdapter
//        binding.StoresContainer.layoutManager = LinearLayoutManager(applicationContext)
//
//        mStoreViewModel.getAllStores.observe(this, Observer {stores ->
//            storeAdapter.setData(stores)
//        })
//
//        binding.StoreBackButton.setOnClickListener {
//            finish()
//        }
//
//        binding.AddStoreButton.setOnClickListener {
//            val intent = Intent(this, AddStore::class.java)
//            startActivity(intent)
//        }
//
//
//
//
//
//    }
//
//
//
//
//
//}