package uz.easycongroup.smartenergy.presentation.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showLongToast(message: String?) {
    if(message.isNullOrEmpty()) return

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(message: String?) {
    if(message.isNullOrEmpty()) return

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(message: String?) =
    requireContext().showLongToast(message)

fun Fragment.showLongToast(message: Int) =
    requireContext().showLongToast(message)

fun Fragment.showShortToast(message: String?) =
    requireContext().showShortToast(message)

fun Fragment.showShortToast(message: Int) =
    requireContext().showShortToast(message)

fun Context.showToastInBottom(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .show()
}

fun Context.showToastInCenter(message: String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun Fragment.showToastInBottom(message: String) =
    requireContext().showToastInBottom(message)

fun Fragment.showToastInCenter(message: String) =
    requireContext().showToastInCenter(message)
