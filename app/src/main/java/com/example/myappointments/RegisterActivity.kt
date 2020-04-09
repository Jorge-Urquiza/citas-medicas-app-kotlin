package com.example.myappointments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tvGoToLogin.setOnClickListener{
            Toast.makeText(this,"Toast funcionando", Toast.LENGTH_LONG).show();
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
