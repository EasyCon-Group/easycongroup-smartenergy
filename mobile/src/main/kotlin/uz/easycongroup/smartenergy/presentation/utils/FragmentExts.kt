package uz.easycongroup.smartenergy.presentation.utils

import android.view.View
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment

val Fragment.onBackPressedDispatcher: OnBackPressedDispatcher
    get() = requireActivity().onBackPressedDispatcher

val Fragment.hasFocus: Boolean
    get() = activity?.currentFocus != null

val Fragment.currentFocus: View?
    get() = activity?.currentFocus