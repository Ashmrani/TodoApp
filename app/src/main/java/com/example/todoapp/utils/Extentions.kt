package com.example.todoapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import com.google.android.material.snackbar.Snackbar

fun Activity.hideKeyboard() {
    try {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        findViewById<View>(android.R.id.content)?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

fun Snackbar.setMessageColor(color: Int): Snackbar {
    val tv = view.findViewById(R.id.snackbar_text) as TextView
    tv.setTextColor(color)

    return this
}

fun Activity.getCompatColor(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Activity.getCompatDrawable(@DrawableRes drawableId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableId)
}

fun Activity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = Color.TRANSPARENT
    } else {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    setRootView()
}

private fun Activity.setRootView() {
    val parent =
        findViewById<View>(android.R.id.content) as ViewGroup
    var i = 0
    val count = parent.childCount
    while (i < count) {
        val childView = parent.getChildAt(i)
        if (childView is ViewGroup) {
            childView.setFitsSystemWindows(true)
            childView.clipToPadding = true
        }
        i++
    }
}