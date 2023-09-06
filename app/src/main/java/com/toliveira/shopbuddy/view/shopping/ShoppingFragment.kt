package com.toliveira.shopbuddy.view.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.toliveira.shopbuddy.databinding.FragmentShoppingBinding
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class ShoppingFragment : Fragment() {

    private lateinit var binding : FragmentShoppingBinding
    private lateinit var mStoreViewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater,container,false)
        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]



        binding.addStoreButton.setOnClickListener{
            val intent = Intent(context,AddStore::class.java)
            startActivity(intent)
        }

        var spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,ArrayList<Store>())
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { storeName ->
            spinnerAdapter.clear()
            storeName.forEach{
                spinnerAdapter.add(it)
            }
        })

        binding.storeSpinner.adapter = spinnerAdapter






        return binding.root
    }
}