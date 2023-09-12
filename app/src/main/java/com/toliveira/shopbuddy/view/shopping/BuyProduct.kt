package com.toliveira.shopbuddy.view.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.ActivityBuyProductBinding

class BuyProduct : AppCompatActivity() {


    lateinit var binding : ActivityBuyProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyProductBinding.inflate(layoutInflater)




        setContentView(binding.root)
    }
}