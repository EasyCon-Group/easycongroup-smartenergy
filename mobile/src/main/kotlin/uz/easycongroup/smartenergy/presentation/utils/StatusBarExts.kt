package uz.easycongroup.smartenergy.presentation.utils

import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import uz.easycongroup.smartenergy.R

fun Fragment.setFullScreenFlags() {
    requireActivity().window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}

fun Fragment.clearFullScreenFlags() {
    requireActivity().window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}

fun Fragment.updateStatusBarToDark(@ColorRes statusBarColor: Int = R.color.color_primary_dark) {
    val window: Window = requireActivity().window
    val decorView = window.decorView

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = requireContext().resources.getColor(statusBarColor)

    WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = false
}

fun Fragment.updateStatusBarToLight(@ColorRes statusBarColor: Int = R.color.color_background_fefefe) {
    val window: Window = requireActivity().window
    val decorView = window.decorView

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = requireContext().resources.getColor(statusBarColor)

    WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = true
}
