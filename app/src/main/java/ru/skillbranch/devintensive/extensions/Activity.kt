package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


fun Activity.hideKeyboard() {
    Log.d("M_Activity","hideKeyboard")
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = main
    val r = Rect()
    rootView.getWindowVisibleDisplayFrame(r)
    return r.height() < rootView.height
}

fun Activity.isKeyboardClosed() = !this.isKeyboardOpen()
