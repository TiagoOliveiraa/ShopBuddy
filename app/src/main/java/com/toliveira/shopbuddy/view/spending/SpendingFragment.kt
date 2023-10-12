package com.toliveira.shopbuddy.view.spending

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.rgb
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.databinding.FragmentSpendingBinding
import com.toliveira.shopbuddy.viewModel.StoreViewModel
import ir.mahozad.android.PieChart

class SpendingFragment : Fragment() {

    private lateinit var binding: FragmentSpendingBinding
    private lateinit var mStoreViewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpendingBinding.inflate(inflater, container, false)
        mStoreViewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        val adapter = SpendingAdapter()
        binding.SpendingContainer.adapter = adapter
        binding.SpendingContainer.layoutManager = LinearLayoutManager(context)

        mStoreViewModel.getAllStores.observe(viewLifecycleOwner,Observer{storeList ->
            adapter.setData(storeList)
        })



        binding.pieChart.apply {
            slices = listOf(
                PieChart.Slice(0.2f, rgb(255,255,150)),
                PieChart.Slice(0.5f, Color.GREEN),
                PieChart.Slice(0.3f, Color.YELLOW)
            )
            labelType = PieChart.LabelType.OUTSIDE
            startAngle = -90
            gradientType = PieChart.GradientType.SWEEP
            holeRatio = 0.5f
        }

        binding.storeManagementButton.setOnClickListener {
            var intent = Intent(this.context,StoreManagement::class.java)
            startActivity(intent)
        }






        return binding.root
    }
}