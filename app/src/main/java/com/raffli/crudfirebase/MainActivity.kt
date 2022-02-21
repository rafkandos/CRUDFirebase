package com.raffli.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //Membuat Kode Permintaan
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //Inisialisasi ID (Button)
        logout.setOnClickListener(this)
        save.setOnClickListener(this)
        showdata.setOnClickListener(this)
    //Mendapatkan Instance Firebase Autentifikasi
        auth = FirebaseAuth.getInstance()
    }
    // Mengecek apakah ada data kosong, digunakan pada Tutorial Selanjutnya

    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }
    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.save -> {
    // Statement program untuk simpan data
            }
            R.id.logout ->
    // Statement program untuk logout/keluar
                AuthUI.getInstance()
                    .signOut(this)

                    .addOnCompleteListener(object : OnCompleteListener<Void> {

                        override fun onComplete(p0: Task<Void>) {
                            Toast.makeText(this@MainActivity, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                                    intent = Intent(applicationContext,
                                LoginActivity::class.java)
                            startActivity(intent)

                            finish()

                        }
                    })
            R.id.showdata -> {
            }
        }
    }
}