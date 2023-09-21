package com.example.agenda

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val cvCategory: CardView = view.findViewById(R.id.cvCategory)
    private val divider: View = view.findViewById(R.id.divider)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        var colorCardView = if (taskCategory.isSelected) {
            R.color.agenda_background_card
        } else {
            R.color.agenda_background_disabled
        }

        cvCategory.setCardBackgroundColor(ContextCompat.getColor(cvCategory.context, colorCardView))

        itemView.setOnClickListener {
            onItemSelected(layoutPosition)
        }

        var color = when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                R.color.agenda_business_category
            }

            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                R.color.agenda_other_category
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                R.color.agenda_personal_category
            }
        }
        divider.setBackgroundColor(
            ContextCompat.getColor(divider.context, color)
        )
    }
}