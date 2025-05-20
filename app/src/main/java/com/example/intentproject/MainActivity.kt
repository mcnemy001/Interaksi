package com.example.intentproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // NIM : 10112384
    // Nama : Aziyusman Maulana
    // Kelas : Android-4
    // Tanggal : 16/05/2025


    private lateinit var txtResultValue : TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == TambahActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getStringExtra(TambahActivity.EXTRA_SELECTED_VALUE)
            txtResultValue.text = "Jenis kelamin yang anda pilih adalah : $selectedValue"
    }}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnSubmit: Button = findViewById(R.id.btn_pindah)
        btnSubmit.setOnClickListener(this)

        val btnData: Button = findViewById(R.id.btn_pindah_dengan_data)
        btnData.setOnClickListener(this)

        val btnObjek: Button = findViewById(R.id.btn_pindah_dengan_objek)
        btnObjek.setOnClickListener(this)

        val btnDial: Button = findViewById(R.id.btn_dial)
        btnDial.setOnClickListener(this)

        val btnAnotherApp: Button = findViewById(R.id.btn_another_app)
        btnAnotherApp.setOnClickListener(this)

        val btnForResult: Button = findViewById(R.id.btn_for_result)
        btnForResult.setOnClickListener(this)

        txtResultValue = findViewById(R.id.txt_result)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_pindah -> {
                val pindahIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(pindahIntent)
            }
            R.id.btn_pindah_dengan_data -> {
                val pindahDataIntent = Intent(this@MainActivity, PindahDenganData::class.java)
                pindahDataIntent.putExtra(PindahDenganData.EXTRA_NAME, "Aziyusman Maulana"  )
                pindahDataIntent.putExtra(PindahDenganData.EXTRA_AGE, "20"  )
                startActivity(pindahDataIntent)
            }
            R.id.btn_pindah_dengan_objek -> {
                val person = Person(
                    "Aziyusman Maulana",
                    20,
                    "aziyusman@gmail.com",
                    "Bandung"
                )

                val pindahObjekIntent = Intent(this@MainActivity, PindahDenganObjek::class.java)
                pindahObjekIntent.putExtra(PindahDenganObjek.EXTRA_PERSON, person)
                startActivity(pindahObjekIntent)
            }

            R.id.btn_dial -> {
                val phoneNumer = "08123456789"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumer"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_another_app -> {
                val openAnotherApps = Intent(Intent.ACTION_MAIN)
                openAnotherApps.setPackage("com.google.android.youtube")
                startActivity(openAnotherApps)
            }

            R.id.btn_for_result -> {
                val forResultIntent = Intent(this@MainActivity, TambahActivity::class.java)
                // startActivity(forResultIntent)

                resultLauncher.launch(forResultIntent)
            }

        }
    }
}