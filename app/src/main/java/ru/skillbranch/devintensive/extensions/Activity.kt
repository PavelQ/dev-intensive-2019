package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.util.Log
import android.view.View


fun Activity.hideKeyboard() {
    Log.d("M_Activity","hideKeyboard")
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}