package uz.easycongroup.smartenergy.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun DialogFragment.showSoftKeyboard(view: View? = null) =
    requireActivity().showSoftKeyboard(view)

fun Fragment.showSoftKeyboard(view: View? = null) =
    requireActivity().showSoftKeyboard(view)

fun Fragment.hideSoftKeyboard(view: View? = null) =
    requireActivity().hideSoftKeyboard(view)

fun Activity.showSoftKeyboard(view: View? = null) {
    when {
        view != null -> showSoftKeyboardWithView(view)
        currentFocus != null -> showSoftKeyboardWithView(checkNotNull(currentFocus))
    }
}

fun Activity.hideSoftKeyboard(view: View? = null) {
    when {
        view != null -> hideSoftKeyboardWithView(view)
        currentFocus != null -> hideSoftKeyboardWithView(checkNotNull(currentFocus))
    }
}

fun Context.showSoftKeyboardWithView(view: View) =
    inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)

fun Context.hideSoftKeyboardWithView(view: View) =
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)

private val Context.inputManager: InputMethodManager
    get() = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager