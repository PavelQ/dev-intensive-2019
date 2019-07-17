package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    Log.d("M_Activity","hideKeyboard")
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive)
        Log.d("M_Activity.kt","hideKeyboard true")
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
}