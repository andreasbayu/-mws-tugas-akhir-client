package com.andbayu.mws_tugas_akhir.utils

import android.content.Context
import com.andbayu.mws_tugas_akhir.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private var retrofit: Retrofit? = null

    fun getClient(context: Context) : Retrofit? {

        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

    private fun okhttpClient(context: Context) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}