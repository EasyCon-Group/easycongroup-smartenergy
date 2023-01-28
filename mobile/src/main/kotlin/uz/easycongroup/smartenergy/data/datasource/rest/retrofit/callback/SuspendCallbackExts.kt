package uz.easycongroup.smartenergy.data.datasource.rest.retrofit.callback

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.*
import uz.easycongroup.smartenergy.data.datasource.rest.error.response.DefaultHttpErrorResponse
import java.net.HttpURLConnection.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T : Any> Call<T>.awaitCallback(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body == null) {
                        val invocation = call.request().tag(Invocation::class.java)!!
                        val method = invocation.method()
                        val e = KotlinNullPointerException(
                            "Response from " +
                                    method.declaringClass.name +
                                    '.' +
                                    method.name +
                                    " was null but response body type was declared as non-null"
                        )
                        continuation.resumeWithException(e)
                    } else {
                        continuation.resume(body)
                    }
                } else {
                    val httpException = HttpException(response)
                    continuation.resumeWithException(getException(httpException))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

private fun getException(httpException: HttpException): Exception {
    val errorResponse = DefaultHttpErrorResponse(httpException.code(), httpException.message())

    return when (httpException.code()) {
        HTTP_BAD_REQUEST -> HttpBadRequestException(errorResponse)
        HTTP_UNAUTHORIZED -> HttpUnauthorizedException(errorResponse)
        HTTP_PAYMENT_REQUIRED -> HttpPaymentRequiredException(errorResponse)
        HTTP_FORBIDDEN -> HttpForbiddenException(errorResponse)
        HTTP_NOT_FOUND -> HttpNotFoundException(errorResponse)
        HTTP_INTERNAL_ERROR -> HttpInternalServerErrorException(errorResponse)
        HTTP_CONFLICT -> HttpConflictException(errorResponse)
        HTTP_UNAVAILABLE -> HttpServiceUnavailableException(errorResponse)
        else -> httpException
    }
}
