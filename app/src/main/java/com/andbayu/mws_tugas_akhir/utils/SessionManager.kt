package com.andbayu.mws_tugas_akhir.utils

import android.content.Context
import android.content.SharedPreferences
import com.andbayu.mws_tugas_akhir.utils.Constants.PREFERENCE_KEY
import com.andbayu.mws_tugas_akhir.utils.Constants.TOKEN

class SessionManager(context: Context){

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCE_KEY,
        Context.MODE_PRIVATE
    )

    fun setToken(token: String) {
        with(sharedPreferences.edit()) {
            putString(TOKEN, token)
            commit()
        }
    }

    fun getToken(): String? = sharedPreferences.getString(TOKEN, null)

}