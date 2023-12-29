//package com.toliveira.shopbuddy.shopping
//
//import android.content.Context
//import android.content.Intent
//import android.graphics.Paint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import com.toliveira.shopbuddy.R
//import com.toliveira.data.product.Product
//import com.toliveira.shopbuddy.model_old.Store
//import com.toliveira.shopbuddy.viewModel.ProductViewModel
//
//@Suppress("KotlinConstantConditions")
//class ShoppingAdapter(
//    private var context: Context?,
//    private var currentStore: Store? = null,
//    private var mProductViewModel: ProductViewModel
//) :
//    RecyclerView.Adapter<ShoppingAdapter.MyViewHolder>() {
//
//
//    private var productList = emptyList<com.toliveira.data.product.Product>()
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): MyViewHolder {
//        return MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item_row, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = productList[position]
//        holder.itemView.findViewById<TextView>(R.id.item_shopping).text = currentItem.productName
//        if (currentItem.productStoreId != null) {
//            holder.itemView.findViewById<TextView>(R.id.item_shopping).paintFlags =
//                Paint.STRIKE_THRU_TEXT_FLAG
//        }
//
//
//        holder.itemView.findViewById<ImageView>(R.id.item_add_car).setOnClickListener { v ->
//
//            if (currentItem.productStoreId == null) {
//                if (currentStore != null) {
//                    if (currentStore!!.storeId != -1) {
//                        val intent = Intent(v.context, BuyProduct::class.java)
//                        intent.putExtra("product", currentItem)
//                        intent.putExtra("store", currentStore)
//                        v.context.startActivity(intent)
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "You need to choose a store from the options below..",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(
//                        context,
//                        "You need to choose a store from the options below..",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            } else {
//                Toast.makeText(context, "This item has already been added", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return productList.size
//    }
//
//    fun setData(product: List<com.toliveira.data.product.Product>) {
//        productList = product
//        notifyDataSetChanged()
//    }
//
//
//}