package com.example.myappointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappointments.Model.Appointment
import kotlinx.android.synthetic.main.item_appointment.view.*

class AppointmentAdapter(private val appointments: ArrayList<Appointment>): RecyclerView.Adapter<AppointmentAdapter.ViewHolder>(){
    class ViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView){
        val tvAppointmentId= itemView.tvAppointmenId
        val tvDoctorName= itemView.tvDoctorName
        val tvScheduledDate= itemView.tvScheduledDate
        val tvScheduledTime= itemView.tvScheduledTime
    }
    // crea un view holder y devuelve un objeto de esa clase , inflates XML items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appointment,parent,false)
        )
    }
    // retona la cantidad de elementos
    override fun getItemCount(): Int {
        return appointments.size
    }
    //Asociar la data del dataset con la vista inflada
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]

        holder.tvAppointmentId.text=appointment.id.toString()
        holder.tvDoctorName.text=appointment.doctorName
        holder.tvScheduledDate.text=appointment.scheduledDate
        holder.tvScheduledTime.text=appointment.scheduledTime
    }


}