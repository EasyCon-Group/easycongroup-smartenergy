package uz.easycongroup.smartenergy.presentation.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    runCatching {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}