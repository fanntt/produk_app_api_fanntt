package com.fanntt.produkappapifanntt.services

import com.fanntt.produkappapifanntt.model.ResponseProduk
import retrofit2.Call
import retrofit2.http.GET

interface ProdukService {
    @GET("products")//endpoint
    fun getAllProduk() : Call<ResponseProduk>
}