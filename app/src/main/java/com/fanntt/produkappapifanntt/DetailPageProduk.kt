package com.fanntt.produkappapifanntt

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailPageProduk : AppCompatActivity() {

    private lateinit var txtTitle :TextView
    private lateinit var txtDescription :TextView
    private lateinit var txtCategory :TextView
    private lateinit var txtPrice :TextView
    private lateinit var txtStock :TextView
    private lateinit var txtBrand :TextView
    private lateinit var imgThumbnail :ImageView
    private lateinit var cardProduk: CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page_produk)

        txtTitle=findViewById(R.id.txtTitle)
        txtDescription=findViewById(R.id.txtDescription)
        txtCategory=findViewById(R.id.txtCategory)
        txtPrice=findViewById(R.id.txtPrice)
        txtStock=findViewById(R.id.txtStock)
        txtBrand=findViewById(R.id.txtBrand)
        imgThumbnail=findViewById(R.id.imgThumbnail)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //getData
        val gambar = intent.getStringExtra("gambar")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val brand = intent.getStringExtra("brand")
        val price = intent.getDoubleExtra("price",0.0)
        val stock = intent.getIntExtra("stock",0)
        val category = intent.getStringExtra("category")

        Glide.with(this).load(gambar).centerCrop()
            .into(imgThumbnail)
        txtTitle.text=title
        txtDescription.text=description
        txtBrand.text=brand
        txtPrice.text=price.toString()
        txtStock.text=stock.toString()
        txtCategory.text=category

//        val DetailDescription = intent.getStringExtra("description")
//        val DetailCategory = intent.getStringExtra("category")
//        val DetailPrice = intent.getStringExtra("price")
//        val DetailStock = intent.getStringExtra("stock")
//        val DetailBrand = intent.getStringExtra("price")
//        val DetailThumbnail = intent.getIntExtra("thumbnail",0)

    }
}