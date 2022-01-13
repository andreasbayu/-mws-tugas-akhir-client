package com.andbayu.mws_tugas_akhir.data.api

import com.andbayu.mws_tugas_akhir.data.model.KategoriMenuModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterfaceKategoriMenu {

    @GET("kategorimenu")
    fun getKategoriMenu() : Call<ArrayList<KategoriMenuModel>>

    @POST("kategorimenu")
    @Multipart
    fun postKategoriMenu(
        @Part("nama")
        nama: RequestBody,
    ) : Call<Unit>

    @PUT("kategorimenu")
    fun updateKategoriMenu(
        @Part("nama")
        nama: RequestBody
    ) : Call<Unit>

    @DELETE("kategorimenu/{id}")
    fun deleteKategoriMenu(
        @Path("id")
        id: String
    ) : Call<Unit>
}