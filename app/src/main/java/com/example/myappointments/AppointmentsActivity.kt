package com.example.myappointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappointments.Model.Appointment
import kotlinx.android.synthetic.main.activity_appointments.*

class AppointmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointments)
        val appointments = ArrayList<Appointment>()
        appointments.add(
            Appointment(
                1,
                "Medico test",
                "12/12/2020",
                "3:00 PM"
            )
        )
        appointments.add(
            Appointment(
                2,
                "Medico B",
                "05/11/2020",
                "5:00 PM"
            )
        )
        appointments.add(
            Appointment(
                1,
                "Medico C",
                "05/05/2020",
                "6:00 PM"
            )
        )
        val rvAppointments = findViewById(R.id.rvAppointments) as RecyclerView
        rvAppointments.layoutManager = LinearLayoutManager(this)
        rvAppointments.adapter =  AppointmentAdapter(appointments)

    }
}
