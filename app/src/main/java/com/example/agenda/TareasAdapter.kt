package com.example.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TareasAdapter(private val listTareas: List<Tarea>): RecyclerView.Adapter<TareasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_tarea, parent, false)
        return TareasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTareas.size
    }

    override fun onBindViewHolder(holder: TareasViewHolder, position: Int) {
        holder.render(listTareas[position])
    }
}