package com.andbayu.mws_tugas_akhir.utils

import android.content.Context
import android.widget.Toast

object Utils {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}