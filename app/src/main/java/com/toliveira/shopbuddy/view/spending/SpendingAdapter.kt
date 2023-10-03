package com.toliveira.shopbuddy.view.spending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.view.shopping.ShoppingAdapter
import org.w3c.dom.Text

class SpendingAdapter : RecyclerView.Adapter<SpendingAdapter.MyViewHolder>() {

    private var storeList = emptyList<Store>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.spending_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = storeList[position]

        holder.itemView.findViewById<TextView>(R.id.item_spending_title).text = currentItem.storeName
        holder.itemView.findViewById<TextView>(R.id.item_spending_value).text = currentItem.storeSpending.toString()
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    fun setData(store : List<Store>){
        storeList = store
        notifyDataSetChanged()
    }


}