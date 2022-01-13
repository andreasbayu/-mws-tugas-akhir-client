package com.andbayu.mws_tugas_akhir.data.model


import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("foto_profil")
        val fotoProfil: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("nama")
        val nama: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("username")
        val username: String
    )
}