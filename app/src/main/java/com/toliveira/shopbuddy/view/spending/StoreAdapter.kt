package com.toliveira.shopbuddy.view.spending

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.shopbuddy.R
import com.toliveira.shopbuddy.model.Product
import com.toliveira.shopbuddy.model.Store
import com.toliveira.shopbuddy.viewModel.ProductViewModel
import com.toliveira.shopbuddy.viewModel.StoreViewModel

class StoreAdapter(
    var mProductViewModel: ProductViewModel,
    var mStoreViewModel: StoreViewModel,
    var context: Context
) :
    RecyclerView.Adapter<StoreAdapter.MyViewHolder>() {

    private var storeList = emptyList<Store>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.store_item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = storeList[position]

        holder.itemView.findViewById<TextView>(R.id.itemStoreText).text = currentItem.storeName

        holder.itemView.findViewById<ConstraintLayout>(R.id.itemStoreName).setOnClickListener {v ->
            var intent = Intent(this.context,UpdateStore::class.java)
            intent.putExtra("store",currentItem)
            v.context.startActivity(intent)
        }

        holder.itemView.findViewById<ConstraintLayout>(R.id.itemStoreDeleteButton)
            .setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Delete Store")
                builder.setMessage("Are you sure you want to delete ${currentItem.storeName}")
                builder.setPositiveButton("Yes") { _, _ ->
                    storeDelete(currentItem)
                    Toast.makeText(context, "Store successfully deleted!", Toast.LENGTH_SHORT)
                        .show()
                }
                builder.setNegativeButton("No") { _, _ -> }
                builder.create().show()
            }
    }

    private fun updateStore(currentItem: Store) {
        mStoreViewModel.updateStore(currentItem)
    }


    private fun storeDelete(store: Store) {
        mProductViewModel.clearProduct(store.storeId)
        mStoreViewModel.deleteStore(store)
    }

    fun setData(store: List<Store>) {
        storeList = store
        notifyDataSetChanged()
    }


}