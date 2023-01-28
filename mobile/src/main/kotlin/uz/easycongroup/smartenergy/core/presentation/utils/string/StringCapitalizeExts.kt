package uz.easycongroup.smartenergy.core.presentation.utils.string

import java.util.*

fun String.toCapitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }
}