package uz.easycongroup.smartenergy.data.datasource.rest.interceptor.auth

import okhttp3.Credentials.basic
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import uz.easycongroup.smartenergy.BuildConfig.BASIC_PASSWORD
import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.preference.user.UserPreference
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestHeaderKeys.HEADER_AUTHORIZATION

internal class HeaderAuthBasicInterceptor(
    private val authPreference: AuthPreference,
    private val userPreference: UserPreference
) : Interceptor {

    override fun intercept(chain: Chain): Response =
        chain.proceed(addHeaderToRequest(chain.request()))

    private fun addHeaderToRequest(oldRequest: Request): Request = oldRequest.let {
        return@let it.newBuilder()
            .apply {
                addHeader(HEADER_AUTHORIZATION, basic(userPreference.username, BASIC_PASSWORD))
            }
            .url(it.url)
            .build()
    }
}