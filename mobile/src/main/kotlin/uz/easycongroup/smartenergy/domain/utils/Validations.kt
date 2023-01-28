package uz.easycongroup.smartenergy.domain.utils

import java.util.regex.Pattern

internal object Validations {

    fun isPhoneNumberValid(value: String?): Boolean =
        value?.let { Pattern.compile("^(?:[0-9] ?){11,14}[0-9]$").matcher(value).matches() }
            ?: false

    fun isTinValid(value: String?): Boolean =
        value?.length == 9

    fun isPasswordValid(value: String?): Boolean =
        value?.let { it.length > 3 } ?: false
//        value?.let { Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$").matcher(value).matches() } ?: false
//        value?.let { it.length > 1 } ?: false

    fun isCodeValid(value: String?): Boolean =
        value?.length?.let { it >= 4 } ?: false
}