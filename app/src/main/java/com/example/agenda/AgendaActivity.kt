package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AgendaActivity : AppCompatActivity() {

    private val listCategories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )
    private val listTareas = mutableListOf<Tarea>(
        Tarea("Programas", TaskCategory.Personal, false)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTareas: RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tareasAdapter: TareasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)
        initComponent()
        iniUI()
    }

    private fun initComponent(){
        rvCategories = findViewById(R.id.rvCategories)
        rvTareas = findViewById(R.id.rvTareas)
    }

    private fun iniUI(){
        categoriesAdapter = CategoriesAdapter(listCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //Que el Recycler View sea Horizontal
        rvCategories.adapter = categoriesAdapter

        tareasAdapter = TareasAdapter(listTareas)
        rvTareas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTareas.adapter = tareasAdapter
    }
}