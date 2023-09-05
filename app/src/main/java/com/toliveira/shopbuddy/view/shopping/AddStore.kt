package com.toliveira.shopbuddy.view.shopping

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.data.ShopDatabase
import com.toliveira.shopbuddy.data.store.StoreRepository
import com.toliveira.shopbuddy.databinding.ActivityAddStoreBinding
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.StoreViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddStore : AppCompatActivity() {

    private lateinit var binding: ActivityAddStoreBinding
    private lateinit var mStoreViewModel: StoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAddStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mStoreViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)

        binding.buttonAddStore.setOnClickListener {

            addStoreToDb()
        }




    }

    private fun isFieldsEmpty() : Boolean{
        return !(isEmpty(binding.textInputTextStore.text.toString()))
    }

    private fun addStoreToDb() {


        if (isFieldsEmpty()){
            var storeName = binding.textInputTextStore.text.toString()

            var store = Store(0, storeName,0F)
            mStoreViewModel.addStore(store)


            Toast.makeText(this, "Store successfully added!", Toast.LENGTH_LONG).show()
            finish()

        }
        else{
            Toast.makeText(this, "Please fill all fields..", Toast.LENGTH_SHORT).show()
        }

    }

}