package uz.easycongroup.smartenergy.data.datasource.rest.error.handler

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.*
import java.io.IOException
import java.net.HttpURLConnection

internal class ErrorResponseHandler : HttpErrorResponseHandler {

    override fun onHandleError(
        httpErrorCode: Int, errorResponse: HttpErrorResponse
    ): IOException? = when (httpErrorCode) {
        HttpURLConnection.HTTP_BAD_REQUEST ->
            throw HttpBadRequestException(errorResponse)
        HttpURLConnection.HTTP_UNAUTHORIZED ->
            throw HttpUnauthorizedException(errorResponse)
        HttpURLConnection.HTTP_PAYMENT_REQUIRED ->
            throw HttpPaymentRequiredException(errorResponse)
        HttpURLConnection.HTTP_FORBIDDEN ->
            throw HttpForbiddenException(errorResponse)
        HttpURLConnection.HTTP_NOT_FOUND ->
            throw HttpNotFoundException(errorResponse)
        HttpURLConnection.HTTP_INTERNAL_ERROR ->
            throw HttpInternalServerErrorException(errorResponse)
        HttpURLConnection.HTTP_CONFLICT ->
            throw HttpConflictException(errorResponse)
        HttpURLConnection.HTTP_UNAVAILABLE ->
            throw HttpServiceUnavailableException(errorResponse)
        else -> null
    }
}