package com.toliveira.shopbuddy.view.shopping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.view.list.ListAdapter
import com.toliveira.shopbuddy.viewModel.ProductViewModel

@Suppress("KotlinConstantConditions")
class ShoppingAdapter(
    context: Context?,
    currentStore: Store? = null,
    mProductViewModel: ProductViewModel
) :
    RecyclerView.Adapter<com.toliveira.shopbuddy.view.shopping.ShoppingAdapter.MyViewHolder>() {


    private var productList = emptyList<Product>()
    private var currentStore = currentStore
    private var context = context
    private var mProductViewModel = mProductViewModel

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


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

        holder.itemView.findViewById<ImageView>(R.id.item_delete_shopping).setOnClickListener { v ->

            if (currentStore != null) {
                if (currentStore!!.storeId != -1) {
                    val intent = Intent(v.context, BuyProduct::class.java)
                    intent.putExtra("product", currentItem)
                    intent.putExtra("store", currentStore)
                    v.context.startActivity(intent)
                } else {
                    Toast.makeText(
                        context,
                        "You need to choose a store from the options below..",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "You need to choose a store from the options below..",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: List<Product>) {
        productList = product
        notifyDataSetChanged()
    }


}