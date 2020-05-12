package com.example.myappointments

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.card_view_step1.*
import kotlinx.android.synthetic.main.card_view_step2.*
import kotlinx.android.synthetic.main.card_view_step2.btnNext2
import kotlinx.android.synthetic.main.card_view_step2.etScheduledDate
import kotlinx.android.synthetic.main.card_view_step2.radioGroupLeft
import kotlinx.android.synthetic.main.card_view_step2.radioGroupRight
import kotlinx.android.synthetic.main.card_view_step2.spinnerDoctors
import kotlinx.android.synthetic.main.card_view_step3.*
import java.util.*

class CreateAppointmentActivity : AppCompatActivity() {
    private val selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)
        btnNext.setOnClickListener {
            if (edDescription.text.toString().length < 5) {
                edDescription.error = "Descripcion muy corta por favor ingrese al menos 5 letras"
            } else {
                cvStep1.visibility = View.GONE
                cvStep2.visibility = View.VISIBLE
            }

        }
        btnNext2.setOnClickListener{
            showAppointmentToConfirm()
            cvStep2.visibility = View.GONE
            cvStep3.visibility = View.VISIBLE
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

    private fun showAppointmentToConfirm() {
        tvConfirmDescription.text=edDescription.text.toString()
        tvConfirmSpecialty.text=spinnerSpecialties.selectedItem.toString()
        tvConfirmDoctorName.text= spinnerDoctors.selectedItem.toString()
        tvConfirmDate.text= etScheduledDate.text.toString()
        tvConfirmTime.text=selectedRadioButton?.text.toString() // por si viene null
        val selectedRadioId= radioGroupType.checkedRadioButtonId // retoorna un INT
        val selectedType= radioGroupType.findViewById<RadioButton>(selectedRadioId) // busco el radio con ese valor int
        tvConfirmType.text= selectedType.text.toString()
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

    private fun Int.twoDigits(): String {
        return if (this > 9) this.toString() else "0$this"
    }

    /* Otra opcion
     fun Int.twoDigits()= if (this <= 9) this.toString() else "0$this"
     */
    override fun onBackPressed() {
        if(cvStep3.visibility==View.VISIBLE){
            cvStep3.visibility=View.GONE
            cvStep2.visibility=View.VISIBLE
        }
        if (cvStep2.visibility == View.VISIBLE) {
            cvStep2.visibility = View.GONE
            cvStep1.visibility = View.VISIBLE
        } else { // paso1
            showAlertDialogAppointment()
        }

    }

    private fun showAlertDialogAppointment() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_exit_tittle))
        builder.setMessage(getString(R.string.dialog_exit_message))
        builder.setPositiveButton(getString(R.string.dialog_appointment_exit_positive)) { _, _ ->
            finish()
        }
        builder.setNegativeButton(getString(R.string.dialog_appointment_exit_negative)) { dialog, witch ->
            dialog.dismiss() //ocultar el dialog
        }
        (builder.create()).show()
    }
}
