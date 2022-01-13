package com.andbayu.mws_tugas_akhir.ui.view.master

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.api.ApiInterfaceKategoriMenu
import com.andbayu.mws_tugas_akhir.utils.ApiService
import com.andbayu.mws_tugas_akhir.utils.Utils
import com.andbayu.mws_tugas_akhir.utils.Utils.showToast
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDeleteKategoriMenuActivity : AppCompatActivity() {

    private lateinit var edtNama: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private var id: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_kategori_menu)

        edtNama = findViewById(R.id.edt_nama_kategori_menu)
        btnDelete = findViewById(R.id.btn_delete)
        btnUpdate = findViewById(R.id.btn_update)

        id = intent.getStringExtra("id")
        edtNama.setText(intent.getStringExtra("nama"))

        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            delete()
        }
    }


    private fun update() {

        val apiService = ApiService.getClient(this)?.create(ApiInterfaceKategoriMenu::class.java)

        val nama = edtNama.text.toString()

        val isInputEmpty = checkInputIsEmpty(
            nama,
        )

        if (isInputEmpty) return

        val requestBodyNama = RequestBody.create(MediaType.parse("text/plain"), nama)

        val updateKategotiMenu: Call<Unit> = apiService?.updateKategoriMenu(
            requestBodyNama
        )!!

        updateKategotiMenu.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    showToast(this@UpdateDeleteKategoriMenuActivity, "Berhasil merubah data")
                    val intent = Intent(this@UpdateDeleteKategoriMenuActivity, KategoriMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("ERROR", "Message: $t")
            }

        })
    }


    private fun delete() {
        val apiService = ApiService.getClient(this)?.create(ApiInterfaceKategoriMenu::class.java)

        if (id == null) return showToast(this, "Id kosong")

        val deleteKategoriMenu: Call<Unit> = apiService?.deleteKategoriMenu(
            id!!
        )!!

        deleteKategoriMenu.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    showToast(this@UpdateDeleteKategoriMenuActivity, "Berhasil menghapus data")
                    val intent = Intent(this@UpdateDeleteKategoriMenuActivity, KategoriMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("ERROR", "Message: $t")
            }
        })
    }

    private fun checkInputIsEmpty(
        nama: String,
    ) : Boolean {

        var isEmpty = false
        val message: String

        if (nama.isEmpty()) {
            message = "Nama tidak boleh kosong"
            isEmpty = true
            showToast(this, message)
            edtNama.error = message
        }
        return isEmpty
    }
}