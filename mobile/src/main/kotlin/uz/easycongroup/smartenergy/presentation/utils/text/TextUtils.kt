package uz.easycongroup.smartenergy.presentation.utils.text

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object TextUtils {
    fun removeAllCharacters(value: String) =
        value.replace("[^0-9,.]+".toRegex(), "")

    fun clearPrice(value: String) =
        value.replace("[^0-9,. ]+".toRegex(), "")
            .replace(" ", "")

    fun formatMoney(amount: Int): String =
        formatMoney(amount.toLong())

    fun formatMoney(amount: Double?): String {
        if (amount == null) return ""

        val result = runCatching {
            val decimalFormatSymbols = DecimalFormatSymbols().apply {
                decimalSeparator = '.'
                groupingSeparator = ' '
            }
            DecimalFormat("#,##0", decimalFormatSymbols).format(amount)
        }.getOrNull() ?: amount.toString()

        return result
    }


    fun formatMoney(amount: Long?): String {
        if (amount == null) return ""

        val result = runCatching {
            val decimalFormatSymbols = DecimalFormatSymbols().apply {
                decimalSeparator = '.'
                groupingSeparator = ' '
            }
            DecimalFormat("#,##0", decimalFormatSymbols).format(amount)
        }.getOrNull() ?: amount.toString()

        return result
    }
}