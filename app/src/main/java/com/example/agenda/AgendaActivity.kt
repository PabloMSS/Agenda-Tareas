package com.example.agenda

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    private lateinit var fabAddTareas: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)
        initComponent()
        iniUI()
        initListener()
    }

    private fun initComponent(){
        rvCategories = findViewById(R.id.rvCategories)
        rvTareas = findViewById(R.id.rvTareas)
        fabAddTareas = findViewById(R.id.fabAddTareas)
    }

    private fun iniUI(){
        categoriesAdapter = CategoriesAdapter(listCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //Que el Recycler View sea Horizontal
        rvCategories.adapter = categoriesAdapter

        tareasAdapter = TareasAdapter(listTareas)
        rvTareas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTareas.adapter = tareasAdapter
    }

    fun initListener(){
        fabAddTareas.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_tareas)
        dialog.show()
    }
}