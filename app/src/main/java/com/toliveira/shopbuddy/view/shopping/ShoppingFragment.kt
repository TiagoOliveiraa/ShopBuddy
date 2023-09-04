package com.toliveira.shopbuddy.view.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toliveira.shopbuddy.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {

    private lateinit var binding : FragmentShoppingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater,container,false)



        binding.addStoreButton.setOnClickListener{
            val intent = Intent(context,AddStore::class.java)
            startActivity(intent)
        }





        return binding.root
    }
}