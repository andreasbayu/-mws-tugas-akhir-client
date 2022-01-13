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
import com.andbayu.mws_tugas_akhir.utils.Utils.showToast
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertKategoriMenuActivity : AppCompatActivity() {

    private lateinit var edtNama: EditText
    private lateinit var btnInsert: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_kategori_menu)

        edtNama = findViewById(R.id.edt_nama_kategori_menu)
        btnInsert = findViewById(R.id.btn_insert)

        btnInsert.setOnClickListener {
            insertData()
        }

    }

    private fun insertData() {
        val apiService = ApiService.getClient(this)?.create(ApiInterfaceKategoriMenu::class.java)

        val nama = edtNama.text.toString()

        val isInputEmpty = checkInputIsEmpty(
            nama,
        )

        if (isInputEmpty) return

        val requestBodyNama = RequestBody.create(MediaType.parse("text/plain"), nama)

        val addKategoriMenu: Call<Unit>? = apiService?.postKategoriMenu(requestBodyNama)!!

        addKategoriMenu?.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful && response.code() == 201) {
                    showToast(this@InsertKategoriMenuActivity, "Berhasil menambah data")
                    val intent = Intent(this@InsertKategoriMenuActivity, KategoriMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("ERROR", "Message: $t")
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