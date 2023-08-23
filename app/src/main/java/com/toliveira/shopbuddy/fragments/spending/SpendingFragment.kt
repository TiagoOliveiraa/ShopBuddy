package com.toliveira.shopbuddy.fragments.spending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toliveira.shopbuddy.databinding.FragmentSpendingBinding

class SpendingFragment : Fragment() {

    private lateinit var binding: FragmentSpendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpendingBinding.inflate(inflater,container,false)
        return binding.root
    }
}