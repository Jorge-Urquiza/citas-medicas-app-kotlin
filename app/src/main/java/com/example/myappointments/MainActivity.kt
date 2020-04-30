package com.example.myappointments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // se puede acceder directamente gracias al import activty_main.*
        tvGoToRegister.setOnClickListener{
            Toast.makeText(this,"Toast funcionando", Toast.LENGTH_LONG).show();
            val intento1 = Intent(this, RegisterActivity::class.java)
            startActivity(intento1)
        }
        btnLogin.setOnClickListener({
            Toast.makeText(this,"Ingresando....", Toast.LENGTH_LONG).show();
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        })
    }
}
