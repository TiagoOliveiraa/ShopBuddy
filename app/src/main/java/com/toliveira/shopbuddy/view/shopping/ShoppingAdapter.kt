package com.toliveira.shopbuddy.view.shopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.view.list.ListAdapter

class ShoppingAdapter :
    RecyclerView.Adapter<com.toliveira.shopbuddy.view.shopping.ShoppingAdapter.MyViewHolder>() {


    private var productList = emptyList<Product>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingAdapter.MyViewHolder {
        return ShoppingAdapter.MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingAdapter.MyViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.itemView.findViewById<TextView>(R.id.item_shopping).text = currentItem.productName
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: List<Product>){
        productList = product
        notifyDataSetChanged()
    }


}