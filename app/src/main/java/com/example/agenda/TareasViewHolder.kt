package com.example.agenda

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareasViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val tvTarea = view.findViewById<TextView>(R.id.tvTarea)
    private val cbTarea = view.findViewById<CheckBox>(R.id.cbTarea)

    fun render(taks: Tarea){
        tvTarea.text = taks.name

        when(taks.isSelected){
            true -> cbTarea.isSelected
            false -> !cbTarea.isSelected
        }

        

    }
}