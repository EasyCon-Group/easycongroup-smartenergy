package uz.easycongroup.smartenergy.presentation.utils.color

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun Context.getColorWithTheme(colorResId: Int, parentTheme: Resources.Theme? = null) =
    resources.getColor(colorResId, parentTheme ?: theme)

fun Fragment.getColorWithTheme(colorResId: Int, parentTheme: Resources.Theme? = null) =
    requireContext().getColorWithTheme(colorResId, parentTheme)

fun RecyclerView.ViewHolder.getColorWithTheme(colorResId: Int, parentTheme: Resources.Theme? = null) =
    itemView.context.getColorWithTheme(colorResId, parentTheme)

fun ImageView.setColorResource(
    colorResId: Int,
    mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN,
    parentTheme: Resources.Theme? = null
) = setColorFilter(context.getColorWithTheme(colorResId, parentTheme), mode)

fun TextView.setTextColorResource(
    colorResId: Int,
    parentTheme: Resources.Theme? = null
) = setTextColor(context.getColorWithTheme(colorResId, parentTheme))
