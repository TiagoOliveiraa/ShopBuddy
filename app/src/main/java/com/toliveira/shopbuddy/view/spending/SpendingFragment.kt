package com.toliveira.shopbuddy.view.spending

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toliveira.shopbuddy.databinding.FragmentSpendingBinding
import ir.mahozad.android.PieChart

class SpendingFragment : Fragment() {

    private lateinit var binding: FragmentSpendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpendingBinding.inflate(inflater, container, false)


        binding.pieChart.apply {
            slices = listOf(
                PieChart.Slice(0.2f, Color.BLUE, Color.BLUE, "test"),
                PieChart.Slice(0.5f, Color.GREEN),
                PieChart.Slice(0.3f, Color.YELLOW)
            )
            labelType = PieChart.LabelType.OUTSIDE
            startAngle = -90
            gradientType = PieChart.GradientType.SWEEP
            holeRatio = 0f
        }




        return binding.root
    }
}