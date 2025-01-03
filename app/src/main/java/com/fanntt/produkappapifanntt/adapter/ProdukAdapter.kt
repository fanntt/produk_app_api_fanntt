package com.fanntt.produkappapifanntt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fanntt.produkappapifanntt.R
import com.fanntt.produkappapifanntt.model.ModelProduk
import com.fanntt.produkappapifanntt.model.ResponseProduk

class ProdukAdapter(
    private val onClick : (ModelProduk) -> Unit
) : ListAdapter<ModelProduk, ProdukAdapter.ProdukViewHolder>(ProdukCallback) {
    class ProdukViewHolder (itemView: View, val onClick: (ModelProduk) -> Unit):RecyclerView.ViewHolder(itemView){

        private val imgProduk : ImageView = itemView.findViewById(R.id.imgProduk)
        private val txtTitle : TextView = itemView.findViewById(R.id.title)
        private val txtBrand : TextView = itemView.findViewById(R.id.brand)
        private val txtPrice : TextView = itemView.findViewById(R.id.price)

        //cek produk saat ini
        private var currentProduct : ModelProduk? = null

        init {
            itemView.setOnClickListener(){
                currentProduct?.let {
                    onClick(it)
                }
            }
        }
        fun bind(produk: ModelProduk){
            currentProduct = produk
            //set data ke widget
            txtTitle.text = produk.title
            txtBrand.text = produk.brand
            txtPrice.text = produk.price.toString()
            //set gambar
            Glide.with(itemView).load(produk.thumbnail).centerCrop()
                .into(imgProduk)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item_produk,parent,false)
        return ProdukViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = getItem(position)
        holder.bind(produk)
    }
}

object ProdukCallback : DiffUtil.ItemCallback<ModelProduk>(){
    override fun areItemsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelProduk, newItem: ModelProduk): Boolean {
        return oldItem == newItem
    }
}
