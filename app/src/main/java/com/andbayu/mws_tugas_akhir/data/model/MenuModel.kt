package com.andbayu.mws_tugas_akhir.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("kategori_id")
    val kategoriId: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
) : Serializable