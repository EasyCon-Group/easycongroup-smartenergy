package uz.easycongroup.smartenergy.data.datasource.rest.interceptor.common

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class HeaderCommonParamsInterceptor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(addHeaderToRequest(chain.request()))

    private fun addHeaderToRequest(oldRequest: Request): Request = oldRequest.let {
        return@let it.newBuilder()
            .apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
            }
            .url(it.url)
            .build()
    }
}