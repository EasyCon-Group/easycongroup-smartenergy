package uz.easycongroup.smartenergy.presentation.utils.context

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getString(resId: Int) =
    itemView.context.getString(resId)

fun RecyclerView.ViewHolder.getString(resId: Int, vararg: Any) =
    itemView.context.getString(resId, vararg)

fun RecyclerView.ViewHolder.getString(@StringRes resId: Int, vararg formatArgs: Any) =
    itemView.context.getString(resId, formatArgs)