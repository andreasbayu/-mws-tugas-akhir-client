package com.andbayu.mws_tugas_akhir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.ui.view.master.KategoriMenuActivity
import com.andbayu.mws_tugas_akhir.ui.view.master.MenuActivity

class MasterActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var btnKategoriMenu: Button
    private lateinit var btnMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)

        btnKategoriMenu = findViewById(R.id.btn_kategori_menu)
        btnMenu         = findViewById(R.id.btn_menu)

        btnKategoriMenu.setOnClickListener(this)
        btnMenu.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_kategori_menu -> startActivity(Intent(this, KategoriMenuActivity::class.java))
            R.id.btn_menu -> startActivity(Intent(this, MenuActivity::class.java))
        }
    }


}