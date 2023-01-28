package uz.easycongroup.smartenergy.data.repository.auth

import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.rest.service.AuthRestService
import uz.easycongroup.smartenergy.domain.data.repository.auth.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authPreference: AuthPreference,
    private val authRestService: AuthRestService
) : AuthRepository {

}