package uz.easycongroup.smartenergy.data.repository.state

import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.preference.user.UserPreference
import uz.easycongroup.smartenergy.domain.data.repository.state.StateRepository
import javax.inject.Inject

internal class StateRepositoryImpl @Inject constructor(
    private val authPreference: AuthPreference,
    private val userPreference: UserPreference,
) : StateRepository {

    override fun isLoginHasBeen(): Boolean =
        authPreference.isLoginHasBeen

    override fun setLoginHasBeen(value: Boolean) {
        authPreference.isLoginHasBeen = true
    }
}