package uz.easycongroup.smartenergy.data.datasource.preference.auth

import android.content.SharedPreferences

internal class AuthPreference(
    private val sharedPreferences: SharedPreferences
) {

    var isLoginHasBeen: Boolean = false
        get() = sharedPreferences.getBoolean(PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN, false)
        set(value) {
            field = value
            sharedPreferences
                .edit()
                .putBoolean(PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN, value)
                .apply()
        }

    var accessToken: String = ""
        get() = sharedPreferences.getString(PREFERENCE_STRING_ACCESS_TOKEN, "")
            ?.let { return@let if (it.isEmpty()) "" else "Bearer $it" } ?: ""
        set(value) {
            field = value
            sharedPreferences
                .edit()
                .putString(PREFERENCE_STRING_ACCESS_TOKEN, value)
                .apply()
        }

    var refreshToken: String = ""
        get() = sharedPreferences.getString(PREFERENCE_STRING_REFRESH_TOKEN, "") ?: ""
        set(value) {
            field = value

            sharedPreferences
                .edit()
                .putString(PREFERENCE_STRING_REFRESH_TOKEN, value)
                .apply()
        }

    fun clear() =
        sharedPreferences.edit().clear().apply()

    private companion object {
        const val PREFERENCE_BOOLEAN_LOGIN_HAS_BEEN: String = "preference_boolean_is_login_has_been"
        const val PREFERENCE_STRING_ACCESS_TOKEN: String = "preference_string_access_token"
        const val PREFERENCE_STRING_REFRESH_TOKEN: String = "preference_string_refresh_token"
    }
}