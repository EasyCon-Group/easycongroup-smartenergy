package uz.easycongroup.smartenergy.core.presentation.utils.date

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedString(format: String): String =
    SimpleDateFormat(format, Locale("ru")).format(this)

fun Long.toFormattedString(format: String): String =
    toDate().toFormattedString(format)

private const val SECOND_9_999_999_999 = 9999999999L

internal fun Long.toDate(): Date =
    Date(if (this > SECOND_9_999_999_999) this else this * 1000)

internal fun Long.toServerDate(): Long =
    if (this > SECOND_9_999_999_999) this / 1000 else this