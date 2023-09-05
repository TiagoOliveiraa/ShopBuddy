package com.toliveira.shopbuddy.view.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.databinding.FragmentListBinding
import com.toliveira.shopbuddy.viewModel.ProductViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater,container,false)
        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val adapter = ListAdapter(requireContext(),mProductViewModel)
        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())


        mProductViewModel.getAllProducts.observe(viewLifecycleOwner, Observer { product->
            adapter.setData(product)
        })


        binding.listButton.setOnClickListener {
            var intent = Intent(context,AddProductActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}