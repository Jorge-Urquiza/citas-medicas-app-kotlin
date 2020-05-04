package com.example.myappointments

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btnCreateAppointment.setOnClickListener{
            startActivity(Intent(this,CreateAppointmentActivity::class.java))
        }
        btnMyAppointments.setOnClickListener{
            startActivity(Intent(this,AppointmentsActivity::class.java))
        }
        btnLogout.setOnClickListener{
            logoutSessionPreference()
            Toast.makeText(this, "LOGOUT....", Toast.LENGTH_LONG).show();
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun logoutSessionPreference(){
        val preference= getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("session",false)
        editor.apply()
    }
}
