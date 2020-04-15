package com.example.myappointments

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_appointment.*
import java.util.*

class CreateAppointmentActivity : AppCompatActivity() {
    private val selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        btnNext.setOnClickListener {
            cvStep1.visibility = View.GONE
            cvStep2.visibility = View.VISIBLE
        }
        btnConfirmAppointment.setOnClickListener {
            Toast.makeText(this, "Cita registrada correctamente", Toast.LENGTH_LONG).show()
            finish()
        }

        val optionSpecialties = arrayOf("Specialty A ", "Specialty B", "Specialty C")
        spinnerSpecialties.adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_expandable_list_item_1,
            optionSpecialties
        )
        val optionDoctors = arrayOf("Doctor A ", "Doctor B", "Doctor C")
        spinnerDoctors.adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_expandable_list_item_1,
            optionDoctors
        )
    }

    // llamamos esta funcion desde el XML
    fun onClickScheduledDate(v: View) {
        //cuando se haga click sobre el input de fecha nosotros vamos a crear un objeto DatePicker

        selectedRadioButton = null
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH) // dia del mes
        val listener = DatePickerDialog.OnDateSetListener {
            // y = year ; month= mes ; d = day
                datePicker, y, m, d ->

            selectedCalendar.set(y, m, d)
            //  Toast.makeText(this,"$y-$m-$d ", Toast.LENGTH_LONG).show()
            //etScheduledDate.setText("$y-$m-$d") esto es posible pero usaremos un recurso string
            etScheduledDate.setText(
                resources.getString(
                    R.string.date_format,
                    y,
                    m.twoDigits(),
                    d.twoDigits()
                ) // if como expresion
            )
            // para añadir radio dinamicamentes
            displayRadioButtom()

        }
        //Min y Max date
        val datePickerDialog = DatePickerDialog(this, listener, year, month, dayOfMonth)
        val datePicker = datePickerDialog.datePicker
        //new Calendar instance
        val calendar = Calendar.getInstance()
        // set Limites
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_MONTH, 29)
        datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()

    }

    private fun displayRadioButtom() {
        radioGroupLeft.removeAllViews()
        radioGroupRight.removeAllViews()


        val hours = arrayOf("3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM")
        var goToLeft = true
        hours.forEach {
            val radioButtom = RadioButton(this)
            radioButtom.id = View.generateViewId() // no es un entero normal
            radioButtom.text = it
            radioButtom.setOnClickListener { view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true
            }
            if (goToLeft)
                radioGroupLeft.addView(radioButtom)
            else
                radioGroupRight.addView(radioButtom)
            goToLeft = !goToLeft
        }

    }
    private fun Int.twoDigits():String{
        return  if (this > 9) this.toString() else "0$this"
    }
    /* Otra opcion
     fun Int.twoDigits()= if (this <= 9) this.toString() else "0$this"
     */
}
