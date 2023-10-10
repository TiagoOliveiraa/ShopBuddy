package com.toliveira.shopbuddy.view.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel
import kotlinx.coroutines.NonDisposableHandle.parent
import java.security.acl.Owner

class ListAdapter(
    private val context: Context,
    private val mProductViewModel: ProductViewModel,
    private val mStoreViewModel: StoreViewModel,
    private val lifeCycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<com.toliveira.shopbuddy.view.list.ListAdapter.MyViewHolder>() {

    private lateinit var storeList: List<Store>

    private var productList = emptyList<Product>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]

        mStoreViewModel.getAllStores.observe(lifeCycleOwner, Observer { stores ->
            storeList = stores
        })
        holder.itemView.findViewById<TextView>(R.id.item_list).text = currentItem.productName

        holder.itemView.findViewById<ConstraintLayout>(R.id.prodNameConstraint)
            .setOnClickListener { v ->
                val intent = Intent(v.context, ProductUpdateActivity::class.java)
                intent.putExtra("product", currentItem)
                v.context.startActivity(intent)
            }

        holder.itemView.findViewById<ConstraintLayout>(R.id.prodDeleteConstraint)
            .setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Delete Task")
                builder.setMessage("Are you sure you want to delete this product from the list?")
                builder.setPositiveButton("Yes") { _, _ ->
                    if (currentItem.productStoreId != null) {
                        var newStoreSpending = 0F
                        lateinit var storeUpdate: Store
                        updateStoreSpending(currentItem.productStoreId, currentItem)
                    }
                    mProductViewModel.deleteProduct(currentItem)
                    Toast.makeText(context, "Product Successfully deleted!", Toast.LENGTH_SHORT)
                        .show()
                }
                builder.setNeutralButton("No") { _, _ -> }
                builder.create().show()

            }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(product: List<Product>) {
        this.productList = product
        notifyDataSetChanged()
    }

    private fun updateStoreSpending(id: Int, product: Product) {

        var currentStore = storeList.find { it.storeId == id }
        var newSpending =
            currentStore!!.storeSpending - (product.productPrice * product.productQuantity)

        mStoreViewModel.updateStoreSpending(id,newSpending)

    }





}