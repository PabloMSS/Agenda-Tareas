package com.example.agenda

import android.annotation.SuppressLint
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TareasAdapter(var listTareas: List<Tarea>, private val onTaskSelected: (Int) -> Unit): RecyclerView.Adapter<TareasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_tarea, parent, false)
        return TareasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTareas.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: TareasViewHolder, position: Int) {
        holder.render(listTareas[position])
        holder.itemView.findViewById<CheckBox>(R.id.cbTarea).setOnClickListener {
            val handler = Handler()
                handler.postDelayed({
                onTaskSelected(position)
            }, 500)
        }
    }
}