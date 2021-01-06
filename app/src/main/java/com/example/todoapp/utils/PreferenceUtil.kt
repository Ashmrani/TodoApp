package com.example.todoapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import java.lang.reflect.Type

class PreferenceUtil(private val mContext: Context) {

    private val defaultEditor: SharedPreferences.Editor
        get() = defaultSharedPreferences.edit()

    private val defaultSharedPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(mContext)

    fun save(key: String, `object`: Any) {
        val `val` = Gson().toJson(`object`)
        setStringValue(key, `val`)
    }

    operator fun <T> get(key: String, clazz: Class<T>): T {
        val value = getStringValue(key)
        return Gson().fromJson(value, clazz)
    }

    operator fun <T> get(key: String, type: Type): Any? {
        val value = getStringValue(key)
        return Gson().fromJson<Any>(value, type)
    }

    fun removeValue(key: String) {
        defaultEditor.remove(key).commit()
    }

    fun getStringValue(key: String, defaultValue: String = ""): String {
        val value = defaultSharedPreferences.getString(key, defaultValue)
        return if (value.isNullOrEmpty()) {
            ""
        } else {
            value
        }
    }

    fun getBooleanValue(key: String, defaultValue: Boolean): Boolean {
        return defaultSharedPreferences.getBoolean(key, defaultValue)
    }

    fun getIntValue(key: String, defaultValue: Int): Int {
        return defaultSharedPreferences.getInt(key, defaultValue)
    }

    fun setStringValue(key: String, value: String) {
        val editor = defaultEditor
        editor.putString(key, value)
        editor.commit()
    }

    fun setIntValue(key: String, value: Int) {
        val editor = defaultEditor
        editor.putInt(key, value)
        editor.commit()
    }

    fun setBooleanValue(key: String, value: Boolean) {
        val editor = defaultEditor
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun clear() {
        defaultEditor.clear().commit()
    }
}