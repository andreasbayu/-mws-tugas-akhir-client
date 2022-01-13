package com.andbayu.mws_tugas_akhir.data.model


import com.google.gson.annotations.SerializedName

data class KategoriMenuModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)