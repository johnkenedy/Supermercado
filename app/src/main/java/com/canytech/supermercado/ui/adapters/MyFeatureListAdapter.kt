package com.canytech.supermercado.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canytech.supermercado.R
import com.canytech.supermercado.models.ProductFeature
import com.canytech.supermercado.ui.activities.ProductDetailsActivity
import com.canytech.supermercado.utils.Constants
import com.canytech.supermercado.utils.GlideLoader
import kotlinx.android.synthetic.main.item_list_layout.view.*

open class MyFeatureListAdapter(
    private val context: Context,
    private var featureList: ArrayList<ProductFeature>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_list_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val featureModel = featureList[position]

        if (holder is MyViewHolder){
            GlideLoader(context).loadFeatureProductPicture(featureModel.image, holder.itemView.item_img_product)
            holder.itemView.item_title_product.text = featureModel.title
            holder.itemView.item_old_price_product.text = featureModel.old_price
            holder.itemView.tv_cart_item_price.text = featureModel.price
            holder.itemView.tv_cart_item_unit.text = featureModel.unit
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_PRODUCT_ID, featureModel.product_id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return featureList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}


