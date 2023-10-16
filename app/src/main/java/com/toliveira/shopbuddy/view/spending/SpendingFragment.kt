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
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.databinding.FragmentSpendingBinding
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.StoreViewModel
import ir.mahozad.android.PieChart
import java.util.Dictionary
import java.util.Random

class SpendingFragment : Fragment() {

    private lateinit var binding: FragmentSpendingBinding
    private lateinit var mStoreViewModel: StoreViewModel
    private var totalSpent: Float = 0F

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

        mStoreViewModel.getAllStores.observe(viewLifecycleOwner, Observer { storeList ->
            adapter.setData(storeList)
            totalSpent = getTotalSpent(storeList)
            makePieChart(storeList, totalSpent)
        })







        binding.storeManagementButton.setOnClickListener {
            var intent = Intent(this.context, StoreManagement::class.java)
            startActivity(intent)
        }






        return binding.root
    }

    private fun makePieChart(storeList: List<Store>?, totalSpent: Float) {

        var sliceList = mutableListOf<PieChart.Slice>()

        storeList?.forEach { store ->
            if (store.storeSpending > 0) {
                var percent = (store.storeSpending / totalSpent)
                sliceList.add(PieChart.Slice(percent,store.storeColor))
            }
        }

        binding.pieChart.apply {
            slices = sliceList
            labelType = PieChart.LabelType.OUTSIDE
            startAngle = -90
            gradientType = PieChart.GradientType.SWEEP
            holeRatio = 0.5f
        }


    }


    private fun getTotalSpent(storeList: List<Store>): Float {
        var spent = 0F
        storeList.forEach { store ->
            if (store.storeSpending > 0) {
                spent += store.storeSpending
            }
        }
        return spent
    }

    private fun getRandomColor(): Int {
        var rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}