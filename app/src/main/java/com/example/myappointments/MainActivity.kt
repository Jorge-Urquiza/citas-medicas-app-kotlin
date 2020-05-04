package com.example.myappointments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val snackBar by lazy{
        Snackbar.make(mainLayout, R.string.press_back_again, Snackbar.LENGTH_SHORT)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // obtener una preferencia

        if(getStateSession()){
            goToMenuActivty()
        }

        // se puede acceder directamente gracias al import activty_main.*
        tvGoToRegister.setOnClickListener {
            goToFormRegister()
        }
        btnLogin.setOnClickListener {
            // crear la preferencia
            createSessionSharedPreferences()
            goToMenuActivty()
        }

    }

    private fun createSessionSharedPreferences() {
        val preference: SharedPreferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor= preference.edit()
        editor.putBoolean("session", true)
        editor.apply()
    }

    private fun goToFormRegister() {
        Toast.makeText(this, "Formulario....", Toast.LENGTH_LONG).show();
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun goToMenuActivty() {
        Toast.makeText(this, "Ingresando....", Toast.LENGTH_LONG).show();
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
    }
    private fun getStateSession() :Boolean{
        val preference: SharedPreferences = getSharedPreferences("general", Context.MODE_PRIVATE)
        val session = preference.getBoolean("session", false)
        return session
    }

    override fun onBackPressed() {
        // vamos a definir un snackbar (muestra mensajes)
        if(snackBar.isShown){
            super.onBackPressed()
        }else{
            snackBar.show()
        }
    }
}
