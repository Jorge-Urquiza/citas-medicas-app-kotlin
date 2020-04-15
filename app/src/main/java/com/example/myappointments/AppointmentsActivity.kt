package com.example.myappointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)
        val rvAppointments = findViewById(R.id.rvAppointments) as RecyclerView
        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter =  AppointmentAdapter()

    }
}
