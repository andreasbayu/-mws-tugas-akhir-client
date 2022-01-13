package com.andbayu.mws_tugas_akhir.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.andbayu.mws_tugas_akhir.R
import com.andbayu.mws_tugas_akhir.data.api.ApiInterfaceAuth
import com.andbayu.mws_tugas_akhir.utils.ApiService
import com.andbayu.mws_tugas_akhir.data.model.AuthModel
import com.andbayu.mws_tugas_akhir.utils.SessionManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var edtPassword: EditText
    private lateinit var edtUsername: EditText
    private lateinit var btnLogin: Button

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin    = findViewById(R.id.btnLogin)

        sessionManager = SessionManager(this)

        btnLogin.setOnClickListener {
            loginAction()
        }
    }

    private fun loginAction() {
        val username = edtUsername.text.toString()
        val password = edtPassword.text.toString()

        val apiInterfaceAuth = ApiService.getClient(this)?.create(ApiInterfaceAuth::class.java)!!

        val loginCall: Call<AuthModel> = apiInterfaceAuth.login(
            username,
            password
        )

        loginCall.enqueue(object : Callback<AuthModel> {
            override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {

                if (response.code() != 200) return showMessage("Terjadi Kesalahan username atau password")

                val res = response.body()
                val accessToken = res?.accessToken

                if (accessToken != null) {
                    // set jwt token
                    sessionManager.setToken(res.accessToken)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }

            override fun onFailure(call: Call<AuthModel>, t: Throwable) {
                Log.e("ERROR", "MESSAGE: $t")
            }

        })
    }

    fun showMessage(message: String) = Snackbar.make(btnLogin, message, Snackbar.LENGTH_SHORT).show()
}