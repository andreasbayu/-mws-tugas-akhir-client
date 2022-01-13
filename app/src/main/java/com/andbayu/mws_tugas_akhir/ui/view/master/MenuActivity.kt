package com.andbayu.mws_tugas_akhir.ui.view.master

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.api.ApiInterfaceMenu
import com.andbayu.mws_tugas_akhir.data.model.MenuModel
import com.andbayu.mws_tugas_akhir.ui.adapter.MenuAdapter
import com.andbayu.mws_tugas_akhir.utils.ApiService
import com.andbayu.mws_tugas_akhir.utils.Utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActivity : AppCompatActivity() {

    private lateinit var rvMenu: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        rvMenu = findViewById(R.id.rv__menu)
    }

    override fun onResume() {
        super.onResume()
        showRecyclerview()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_insert_menu -> {
                startActivity(Intent(this@MenuActivity, InsertMenuActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerview() {
        val apiService  = ApiService.getClient(this)?.create(ApiInterfaceMenu::class.java)

        val getMenu: Call<ArrayList<MenuModel>> = apiService?.getMenu()!!

        getMenu.enqueue(object : Callback<ArrayList<MenuModel>> {

            override fun onResponse(
                call: Call<ArrayList<MenuModel>>,
                response: Response<ArrayList<MenuModel>>
            ) {
                if (response.code() != 200) return showToast(
                    this@MenuActivity,
                    "Terdasi masalah"
                )
                val body = response.body()

                val data = ArrayList<MenuModel>()

                if (body != null) {
                    data.addAll(body)
                }

                rvMenu.layoutManager = GridLayoutManager(this@MenuActivity, 2)
                rvMenu.adapter = MenuAdapter(data)

            }

            override fun onFailure(call: Call<ArrayList<MenuModel>>, t: Throwable) {
                Log.e("ERROR", "Message: $t")
                showToast(this@MenuActivity, "Terjadi masalah sambungan")
            }

        })
    }
}