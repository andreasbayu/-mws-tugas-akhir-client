package com.andbayu.mws_tugas_akhir.data.api

import com.andbayu.mws_tugas_akhir.data.model.AuthModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterfaceAuth {

    @POST("auth")
    fun login(
        @Query("username")
        username: String,
        @Query("password")
        password: String
    ) : Call<AuthModel>

    // register
}