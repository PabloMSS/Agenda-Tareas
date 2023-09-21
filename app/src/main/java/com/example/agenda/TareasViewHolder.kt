package com.example.agenda

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TareasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTarea = view.findViewById<TextView>(R.id.tvTarea)
    private val cbTarea = view.findViewById<CheckBox>(R.id.cbTarea)


    fun render(task: Tarea) {
        tvTarea.text = task.name

        when (task.isSelected) {
            true -> cbTarea.isChecked = true
            false -> cbTarea.isChecked = false
        }

        val color = when (task.category) {
            TaskCategory.Business -> R.color.agenda_business_category
            TaskCategory.Personal -> R.color.agenda_personal_category
            TaskCategory.Other -> R.color.agenda_other_category
        }

        cbTarea.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTarea.context, color)
        )
    }
}