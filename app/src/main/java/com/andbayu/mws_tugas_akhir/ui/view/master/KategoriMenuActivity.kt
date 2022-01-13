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
import com.andbayu.mws_tugas_akhir.data.api.ApiInterfaceKategoriMenu
import com.andbayu.mws_tugas_akhir.utils.ApiService
import com.andbayu.mws_tugas_akhir.data.model.KategoriMenuModel
import com.andbayu.mws_tugas_akhir.ui.adapter.KategoriMenuAdapter
import com.andbayu.mws_tugas_akhir.utils.Utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KategoriMenuActivity : AppCompatActivity() {

    private lateinit var rvKategoriMenu: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_menu)

        rvKategoriMenu = findViewById(R.id.rv_kategori_menu)
        rvKategoriMenu.setHasFixedSize(true)

        showRecyclerview()
    }

    override fun onResume() {
        super.onResume()
        showRecyclerview()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_kategori_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_insert_kategori_menu -> {
                startActivity(Intent(this, InsertKategoriMenuActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerview() {
        val apiService  = ApiService.getClient(this)?.create(ApiInterfaceKategoriMenu::class.java)

        val getKategoriMenuModel: Call<ArrayList<KategoriMenuModel>> = apiService?.getKategoriMenu()!!

        getKategoriMenuModel.enqueue(object : Callback<ArrayList<KategoriMenuModel>> {

            override fun onResponse(
                call: Call<ArrayList<KategoriMenuModel>>,
                response: Response<ArrayList<KategoriMenuModel>>
            ) {
                if (response.code() != 200) return showToast(this@KategoriMenuActivity,"Terdasi masalah")
                val body = response.body()

                val data = ArrayList<KategoriMenuModel>()

                if (body != null) {
                    data.addAll(body)
                }

                rvKategoriMenu.layoutManager = GridLayoutManager(this@KategoriMenuActivity, 2)
                rvKategoriMenu.adapter = KategoriMenuAdapter(data)

            }

            override fun onFailure(call: Call<ArrayList<KategoriMenuModel>>, t: Throwable) {
                Log.e("ERROR", "Message: $t")
                showToast(this@KategoriMenuActivity, "Terjadi masalah sambungan")
            }

        })
    }
}