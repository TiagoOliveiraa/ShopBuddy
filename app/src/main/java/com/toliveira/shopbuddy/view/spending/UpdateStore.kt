package com.toliveira.shopbuddy.view.spending

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.ActivityUpdateStoreBinding
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class UpdateStore() : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateStoreBinding
    private lateinit var mStoreViewModel: StoreViewModel
    private val store by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("store", Store::class.java)
        } else {
            intent.getParcelableExtra("store")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]


        binding.updateStoreText.setText(store?.storeName)

        binding.updateStoreButton.setOnClickListener {
            updateStore()
        }

    }

    private fun updateStore() {
        var newStore = Store(
            store!!.storeId,
            binding.updateStoreText.text.toString(),
            store!!.storeSpending,
            store!!.storeColor
        )
        mStoreViewModel.updateStore(newStore)
        Toast.makeText(this, "Store successfully updated", Toast.LENGTH_SHORT).show()
        finish()
    }
}