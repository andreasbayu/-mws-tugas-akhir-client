package com.andbayu.mws_tugas_akhir.data.api

import com.andbayu.mws_tugas_akhir.data.model.MenuModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterfaceMenu {

    @GET("menu")
    fun getMenu(): Call<ArrayList<MenuModel>>

    @POST("menu")
    @Multipart
    fun postMenu(
        @Part("nama")
        nama: RequestBody,
        @Part("status")
        status: RequestBody,
        @Part("harga")
        harga: RequestBody,
        @Part("kategori_id")
        kategoriId: RequestBody,
    ) : Call<Unit>

    @PUT("menu")
    fun updateMenu(
        @Part("nama")
        nama: RequestBody,
        @Part("status")
        status: RequestBody,
        @Part("harga")
        harga: RequestBody,
        @Part("kategori_id")
        kategoriId: RequestBody,
    ) : Call<Unit>

    @DELETE("menu/{id}")
    fun deleteMenu(
        @Path("id")
        id: String
    ) : Call<Unit>

}