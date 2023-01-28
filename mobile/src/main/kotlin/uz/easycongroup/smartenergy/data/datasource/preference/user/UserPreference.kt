package uz.easycongroup.smartenergy.data.datasource.preference.user

import android.content.SharedPreferences
import uz.easycongroup.smartenergy.data.models.user.role.UserRole

internal class UserPreference(
    private val sharedPreferences: SharedPreferences
) {

    var userId: Long = 0L
        get() = sharedPreferences.getLong(KEY_USER_ID, 0)
        set(value) {
            field = value
            sharedPreferences.edit().putLong(KEY_USER_ID, value).apply()
        }

    var username: String = ""
        get() = sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
        set(value) {
            field = value
            sharedPreferences.edit().putString(KEY_USER_NAME, value).apply()
        }

    var userRole: UserRole = UserRole.DEFAULT
        get() {
            return sharedPreferences.getString(KEY_USER_ROLE, "")
                .let { UserRole.valueOrDefault(it) }
        }
        set(value) {
            field = value
            sharedPreferences.edit().putString(KEY_USER_ROLE, value.name).apply()
        }

    fun clear() =
        sharedPreferences.edit().clear().apply()

    private companion object {
        const val KEY_USER_ID: String = "user_id"
        const val KEY_USER_NAME: String = "user_name"
        const val KEY_USER_ROLE: String = "user_role"
    }
}