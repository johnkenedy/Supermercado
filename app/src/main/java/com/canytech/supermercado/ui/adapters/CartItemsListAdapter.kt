package com.canytech.supermercado.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canytech.supermercado.R
import com.canytech.supermercado.models.CartItem
import com.canytech.supermercado.ui.activities.ProductDetailsActivity
import com.canytech.supermercado.utils.Constants
import com.canytech.supermercado.utils.GlideLoader
import kotlinx.android.synthetic.main.item_cart_layout.view.*
import kotlinx.android.synthetic.main.item_list_layout.view.*
import kotlinx.android.synthetic.main.item_list_layout.view.item_img_product
import kotlinx.android.synthetic.main.item_list_layout.view.tv_cart_item_price
import kotlinx.android.synthetic.main.item_list_layout.view.tv_cart_item_unit

open class CartItemsListAdapter(
    private val context: Context,
    private var list: ArrayList<CartItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cart_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {
            GlideLoader(context).loadProductPicture(
                model.image,
                holder.itemView.iv_cart_item_img
            )
            holder.itemView.tv_cart_item_title.text = model.title
            holder.itemView.tv_cart_item_unit.text = model.unit
            holder.itemView.tv_cart_item_quantity.text = model.stock_quantity
            holder.itemView.tv_cart_item_price.text = model.price
            holder.itemView.tv_cart_quantity.text = model.cart_quantity
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_PRODUCT_ID, model.product_id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}


