package com.andbayu.mws_tugas_akhir.ui.view.master

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.api.ApiInterfaceMenu
import com.andbayu.mws_tugas_akhir.utils.ApiService
import com.andbayu.mws_tugas_akhir.utils.Utils.showToast
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertMenuActivity : AppCompatActivity() {

    private lateinit var edtNama: EditText
    private lateinit var edtHarga: EditText
    private lateinit var edtStatus: EditText
    private lateinit var edtIdKategori: EditText
    private lateinit var btnInsert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_menu)

        edtNama         = findViewById(R.id.edt_nama_menu)
        edtHarga        = findViewById(R.id.edt_harga)
        edtStatus       = findViewById(R.id.edt_status)
        edtIdKategori   = findViewById(R.id.edt_id_kategori_menu)
        btnInsert       = findViewById(R.id.btn_insert)

        btnInsert.setOnClickListener {
            insert()
        }
    }


    private fun insert() {
        val apiService = ApiService.getClient(this)?.create(ApiInterfaceMenu::class.java)

        val isInputEmpty = checkInputIsEmpty(
            edtNama.text.toString(),
            edtHarga.text.toString(),
            edtStatus.text.toString(),
            edtIdKategori.text.toString()
        )

        if (isInputEmpty) return

        val nama = RequestBody.create(MediaType.parse("text/plain"), edtNama.text.toString())
        val idKategoriMenu = RequestBody.create(MediaType.parse("text/plain"), edtIdKategori.text.toString())
        val harga = RequestBody.create(MediaType.parse("text/plain"), edtHarga.text.toString())
        val status = RequestBody.create(MediaType.parse("text/plain"), edtStatus.text.toString())

        val postMenu: Call<Unit> = apiService?.postMenu(
            nama = nama,
            harga = harga,
            kategoriId = idKategoriMenu,
            status = status
        )!!

        postMenu.enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful && response.code() == 201) {
                    showToast(this@InsertMenuActivity, "Berhasil menambah data")
                    val intent = Intent(this@InsertMenuActivity, MenuActivity::class.java)
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
        harga: String,
        status: String,
        idkategori: String
    ) : Boolean {

        var isEmpty = false
        var message: String

        if (nama.isEmpty()) {
            message = "Nama tidak boleh kosong"
            isEmpty = true
            showToast(this, message)
            edtNama.error = message
        }
        if (harga.isEmpty()) {
            message = "harga tidak boleh kosong"
            isEmpty = true
            showToast(this, message)
            edtHarga.error = message
        }
        if (status.isEmpty()) {
            message = "status tidak boleh kosong"
            isEmpty = true
            showToast(this, message)
            edtStatus.error = message
        }
        if (idkategori.isEmpty()) {
            message = "id kategori tidak boleh kosong"
            isEmpty = true
            showToast(this, message)
            edtIdKategori.error = message
        }
        return isEmpty
    }
}