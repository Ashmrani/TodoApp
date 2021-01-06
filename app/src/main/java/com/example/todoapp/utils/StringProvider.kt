package com.example.todoapp.utils

import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface StringProvider {
    fun provide(@StringRes resourceID: Int): String
    fun provideArray(@ArrayRes resourceID: Int): Array<String>
    fun provide(@StringRes resourceID: Int, vararg formatArgs: Any?): String
}