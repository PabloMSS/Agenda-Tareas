package com.example.agenda

sealed class TaskCategory {
    object Personal: TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}