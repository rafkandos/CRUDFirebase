package com.raffli.crudfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_update_data.*

class UpdateData : AppCompatActivity() {
    //Deklarasi Variable
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNIM: String? = null
    private var cekNama: String? = null
    private var cekJurusan: String? = null
    private var cekAlamat: String? = null
    private var cekJkel: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        supportActionBar!!.title = "Update Data"
        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance("https://crudfirebasekotlin-7bff0-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        data //memanggil method "data"
        update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Mendapatkan Data Mahasiswa yang akan dicek
                cekNIM = new_nim.getText().toString()
                cekNama = new_nama.getText().toString()
                cekJurusan = new_jurusan.getText().toString()
                cekAlamat = new_alamat.getText().toString()
                cekJkel = new_jkel.getSelectedItem().toString()
                //Mengecek agar tidak ada data yang kosong, saat proses update
                if (isEmpty(cekNIM!!) || isEmpty(cekNama!!) ||
                    isEmpty(cekJurusan!!)) {
                    Toast.makeText(
                        this@UpdateData,
                        "Data tidak boleh ada yang kosong",

                        Toast.LENGTH_SHORT

                    ).show()
                } else {
                    /*Menjalankan proses update data.
                    Method Setter digunakan untuk mendapakan data baru yang diinputkan User.*/
                    val setMahasiswa = data_mahasiswa()

                    setMahasiswa.nim = new_nim.getText().toString()
                    setMahasiswa.nama = new_nama.getText().toString()
                    setMahasiswa.jurusan = new_jurusan.getText().toString()
                    setMahasiswa.alamat = new_alamat.getText().toString()
                    setMahasiswa.jkel = new_jkel.getSelectedItem().toString()
                    updateMahasiswa(setMahasiswa)
                }
            }
        })
    }
    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private fun isEmpty(s: String): Boolean {
        return TextUtils.isEmpty(s)
    }
    //Menampilkan data yang akan di update
    private val data: Unit
        private get() {
//Menampilkan data dari item yang dipilih sebelumnya
            val getNIM = intent.extras!!.getString("dataNIM")
            val getNama = intent.extras!!.getString("dataNama")
            val getJurusan = intent.extras!!.getString("dataJurusan")
            val getAlamat = intent.extras!!.getString("dataAlamat")
            val getJkel = intent.extras!!.getString("dataJkel")
            new_nim!!.setText(getNIM)
            new_nama!!.setText(getNama)
            new_jurusan!!.setText(getJurusan)
            new_alamat!!.setText(getAlamat)
            new_jkel!!.setSelection(resources.getStringArray(R.array.jkel).indexOf(getJkel))
        }
    //Proses Update data yang sudah ditentukan
    private fun updateMahasiswa(mahasiswa: data_mahasiswa) {
        val userID = auth!!.uid
        val getKey = intent.extras!!.getString("getPrimaryKey")
        database!!.child("Admin")
            .child(userID!!)
            .child("Mahasiswa")
            .child(getKey!!)
            .setValue(mahasiswa)
            .addOnSuccessListener {
                new_nim!!.setText("")

                new_nama!!.setText("")
                new_jurusan!!.setText("")
                Toast.makeText(this@UpdateData, "Data Berhasil diubah",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        startActivity(Intent(this@UpdateData, MyListData::class.java))
    }
}