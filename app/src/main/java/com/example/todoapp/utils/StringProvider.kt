package com.example.todoapp.utils

import androidx.annotation.StringRes

interface StringProvider {
    fun provide(@StringRes resourceID: Int): String
}