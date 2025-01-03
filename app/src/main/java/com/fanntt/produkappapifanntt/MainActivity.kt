package com.fanntt.produkappapifanntt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fanntt.produkappapifanntt.adapter.ProdukAdapter
import com.fanntt.produkappapifanntt.model.ModelProduk
import com.fanntt.produkappapifanntt.model.ResponseProduk
import com.fanntt.produkappapifanntt.services.ApiClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call : Call<ResponseProduk>
    private lateinit var produkAdapter: ProdukAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout=findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_products)

        produkAdapter= ProdukAdapter { modelProduk : ModelProduk -> produkOnClick(modelProduk)  }
        recyclerView.adapter = produkAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext , LinearLayoutManager.VERTICAL,false
        )
        // swipe refresh
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()
    }


    private fun produkOnClick(modelProduk: ModelProduk) {
        val intent = Intent(this, DetailPageProduk::class.java)
        intent.putExtra("gambar",modelProduk.thumbnail)
        intent.putExtra("title",modelProduk.title)
        intent.putExtra("description",modelProduk.description)
        intent.putExtra("stock",modelProduk.stock)
        intent.putExtra("price",modelProduk.price)
        intent.putExtra("brand",modelProduk.brand)


        startActivity(intent)
    }

    private fun getData() {
        // proses untuk dapatkan respons
        swipeRefreshLayout.isRefreshing = true
        call = ApiClient.produkService.getAllProduk()
        call.enqueue(object : Callback<ResponseProduk> {
            override fun onResponse(
                call: Call<ResponseProduk>,
                response: retrofit2.Response<ResponseProduk>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    produkAdapter.submitList(response.body()?.products)
                    produkAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<ResponseProduk>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(
                    applicationContext,
                    t.localizedMessage, Toast.LENGTH_SHORT
                ).show()

            }
        })
    }
}



