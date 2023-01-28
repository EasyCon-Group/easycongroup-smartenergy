package uz.easycongroup.smartenergy.data.datasource.rest.retrofit.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import uz.easycongroup.smartenergy.data.datasource.rest.error.handler.HttpErrorResponseHandler
import uz.easycongroup.smartenergy.data.datasource.rest.error.mapper.HttpErrorResponseMapper
import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse

internal class HttpErrorDispatcherInterceptor private constructor(
    private val handlers: Array<HttpErrorResponseHandler>,
    private val mappers: Array<HttpErrorResponseMapper>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
//        val response: Response?
//
//        try {
//            response = chain.proceed(chain.request())
//        } catch (exception: Exception) {
//            if (BuildConfig.DEBUG) Timber.d(exception, "Error when proceeding")
//            throw exception
//        }
//
//        val httpErrorCode: Int = response.code
//
//        if (httpErrorCode in 400..503) {
//            val errorResponse: HttpErrorResponse = mappers.mapNotNull {
//                it.onHandleResponse(httpErrorCode, response.peekBody(Long.MAX_VALUE).string())
//            }.firstOrNull() ?: getErrorResponse(httpErrorCode)
//
//            Timber.e("", "httpErrorCode = $httpErrorCode, errorResponse = $errorResponse")
//
//            val firstAvailableErrorHandler: Throwable? = handlers.mapNotNull {
//                it.onHandleError(httpErrorCode, errorResponse)
//            }.firstOrNull()
//
//            if (firstAvailableErrorHandler != null)
//                throw firstAvailableErrorHandler.also { Timber.e(it) }
//            else throw RestErrorException(errorResponse).also { Timber.e(it) }
//        }
//
//        return response

        return chain.proceed(chain.request())
    }

    private fun getErrorResponse(httpErrorCode: Int, errorMessage: String): HttpErrorResponse =
        object : HttpErrorResponse {
            override val httpErrorCode: Int = httpErrorCode
            override val errorMessage: String = errorMessage
        }

    companion object {

        class Builder {
            private val handlers: MutableList<HttpErrorResponseHandler> = arrayListOf()
            private val mappers: MutableList<HttpErrorResponseMapper> = arrayListOf()

            fun setResponseHandlers(vararg handlers: HttpErrorResponseHandler): Builder {
                this.handlers.apply { clear(); addAll(handlers) }
                return this
            }

            fun setResponseMappers(vararg mappers: HttpErrorResponseMapper): Builder {
                this.mappers.apply { clear(); addAll(mappers) }
                return this
            }

            fun build(): HttpErrorDispatcherInterceptor =
                HttpErrorDispatcherInterceptor(
                    handlers = handlers.toTypedArray(),
                    mappers = mappers.toTypedArray()
                )
        }
    }
}