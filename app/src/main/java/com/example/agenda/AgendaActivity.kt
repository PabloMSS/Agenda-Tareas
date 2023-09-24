package com.example.agenda

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.logging.Logger

class AgendaActivity : AppCompatActivity() {

    private val listCategories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )
    private val listTareas = mutableListOf<Tarea>()

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTareas: RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tareasAdapter: TareasAdapter

    private lateinit var fabAddTareas: FloatingActionButton
    private lateinit var rgCategories: RadioGroup
    private lateinit var etDialog: EditText
    private lateinit var rbBusiness: RadioButton
    private lateinit var rbPersonal: RadioButton
    private lateinit var rbOther: RadioButton
    private lateinit var btnTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)
        initComponent()
        iniUI()
        initListener()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTareas = findViewById(R.id.rvTareas)
        fabAddTareas = findViewById(R.id.fabAddTareas)
    }

    private fun iniUI() {
        categoriesAdapter =
            CategoriesAdapter(listCategories) { position -> updateCategories(position) }
        rvCategories.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        ) //Que el Recycler View sea Horizontal
        rvCategories.adapter = categoriesAdapter

        tareasAdapter = TareasAdapter(listTareas) { position -> onItemSelected(position) }
        rvTareas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTareas.adapter = tareasAdapter
    }

    fun initListener() {
        fabAddTareas.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_tareas)
        dialog.show()

        etDialog = dialog.findViewById(R.id.etDialog)
        rgCategories = dialog.findViewById(R.id.rgCategories)
        rbBusiness = dialog.findViewById(R.id.rbBusiness)
        rbPersonal = dialog.findViewById(R.id.rbPersonal)
        rbOther = dialog.findViewById(R.id.rbOther)
        btnTarea = dialog.findViewById(R.id.btnTarea)

        btnTarea.setOnClickListener {
            var selectedID = rgCategories.checkedRadioButtonId
            var textTarea = etDialog.text.toString()
            var nameTarea = rgCategories.findViewById<RadioButton>(selectedID).text

            var rbTaskCategory: TaskCategory = when (nameTarea) {
                getString(R.string.agenda_business) -> TaskCategory.Business
                getString(R.string.agenda_personal) -> TaskCategory.Personal
                else -> TaskCategory.Other
            }

            if (textTarea.isNotEmpty()) {
                var nuevaTarea: Tarea = Tarea(textTarea, rbTaskCategory, false)
                listTareas.add(nuevaTarea)
                for (item in listTareas){
                    Log.e("LOG", item.isSelected.toString())
                }
                updatedAdapter()
                dialog.hide()
            } else {
                Toast.makeText(this, "Introduce el nombre de la Tarea", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onItemSelected(position: Int) {
        listTareas[position].isSelected = !listTareas[position].isSelected
        listTareas.remove(listTareas[position])
        updatedAdapter()
    }

    fun updateCategories(position: Int) {
        listCategories[position].isSelected = !listCategories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateListCategories()
    }

    fun updateListCategories() {
        val selectedCategories: List<TaskCategory> = listCategories.filter { it.isSelected }
        val newTareas = listTareas.filter { selectedCategories.contains(it.category) }
        tareasAdapter.listTareas = newTareas
        tareasAdapter.notifyDataSetChanged()
    }

    fun updatedAdapter(){
        tareasAdapter.notifyDataSetChanged()
    }
}
