package com.andbayu.mws_tugas_akhir.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andbayu.mws_tugas_akhir.R

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var btnMaster: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMaster = findViewById(R.id.btn_master)
        btnMaster.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_master -> startActivity(Intent(this, MasterActivity::class.java))
        }
    }


}