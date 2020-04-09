package com.example.myappointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_appointment.*

class CreateAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        btnNext.setOnClickListener{
            cvStep1.visibility= View.GONE
            cvStep2.visibility=View.VISIBLE
        }
        btnConfirmAppointment.setOnClickListener{
            Toast.makeText(this,"Cita registrada correctamente",Toast.LENGTH_LONG).show()
            finish()
        }

        val optionSpecialties= arrayOf("Specialty A ", "Specialty B", "Specialty C")
        spinnerSpecialties.adapter= ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
            optionSpecialties)
        val optionDoctors= arrayOf("Doctor A ", "Doctor B", "Doctor C")
        spinnerDoctors.adapter= ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
            optionDoctors)
    }
}
