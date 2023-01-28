package uz.easycongroup.smartenergy.presentation.utils

import android.view.View

private var lastClickTimestamp: Long = 0L

fun View.setThrottledClickListener(delay: Long = 500, listener: (View) -> Unit) {
    setOnClickListener {
        val currentTimestamp: Long = System.currentTimeMillis()
        val delta: Long = currentTimestamp - lastClickTimestamp
        if (delta !in 0..delay) {
            lastClickTimestamp = currentTimestamp
            listener(it)
        }
    }
}