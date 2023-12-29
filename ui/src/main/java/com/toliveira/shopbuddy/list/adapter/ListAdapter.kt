package com.toliveira.shopbuddy.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.list.model.ListUI
import com.toliveira.shopbuddy.model.ProductUI

class ListAdapter(
) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //private lateinit var storeList: List<Store>
    //private lateinit var listUIFactory: ListUIFactory

    private var productList = emptyList<ProductUI>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]


        holder.itemView.findViewById<TextView>(R.id.item_list).text = currentItem.productName

//        holder.itemView.findViewById<ConstraintLayout>(R.id.prodNameConstraint)
//            .setOnClickListener { v ->
//                val intent = Intent(v.context, ProductUpdateActivity::class.java)
//                intent.putExtra("product", currentItem)
//                v.context.startActivity(intent)
//            }

//        holder.itemView.findViewById<ConstraintLayout>(R.id.prodDeleteConstraint)
//            .setOnClickListener {
//                var builder = AlertDialog.Builder(context)
//                builder.setTitle("Delete Task")
//                builder.setMessage("Are you sure you want to delete this product from the list?")
//                builder.setPositiveButton("Yes") { _, _ ->
//                    var newStoreSpending = 0F
//                    lateinit var storeUpdate: Store
//                    updateStoreSpending(currentItem.productStoreId, currentItem)
//                    mProductViewModel.deleteProduct(currentItem)
//                    Toast.makeText(context, "Product Successfully deleted!", Toast.LENGTH_SHORT)
//                        .show()
//                }
//                builder.setNeutralButton("No") { _, _ -> }
//                builder.create().show()
//
//            }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(products: ListUI) {
        this.productList = products.productsList
        notifyDataSetChanged()
    }

//    private fun updateStoreSpending(id: Int, product: com.toliveira.data.product.Product) {
//
//        var currentStore = storeList.find { it.storeId == id }
//        var newSpending =
//            currentStore!!.storeSpending - (product.productPrice * product.productQuantity)
//
//        mStoreViewModel.updateStoreSpending(id, newSpending)
//
//    }


}