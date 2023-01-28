package uz.easycongroup.smartenergy.data.datasource.rest.retrofit.interceptor

import okhttp3.OkHttpClient
import uz.easycongroup.smartenergy.data.datasource.rest.error.dispatcher.HttpErrorDispatcherComponent
import uz.easycongroup.smartenergy.data.datasource.rest.error.handler.HttpErrorResponseHandler
import uz.easycongroup.smartenergy.data.datasource.rest.error.mapper.HttpErrorResponseMapper

internal fun OkHttpClient.Builder.withHttpErrorDispatcher(
    vararg components: HttpErrorDispatcherComponent
): OkHttpClient.Builder = addInterceptor(
    HttpErrorDispatcherInterceptor.Companion.Builder()
        .setResponseHandlers(
            *components.filterIsInstance<HttpErrorResponseHandler>().toTypedArray()
        )
        .setResponseMappers(*components.filterIsInstance<HttpErrorResponseMapper>().toTypedArray())
        .build()
)