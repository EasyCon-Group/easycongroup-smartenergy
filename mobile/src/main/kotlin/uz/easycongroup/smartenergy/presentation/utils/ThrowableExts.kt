package uz.easycongroup.smartenergy.presentation.utils

import retrofit2.HttpException
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.HttpBadRequestException
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.HttpForbiddenException
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.HttpInternalServerErrorException
import uz.easycongroup.smartenergy.data.datasource.rest.error.exception.HttpUnauthorizedException
import uz.easycongroup.smartenergy.data.datasource.rest.retrofit.exception.RestErrorException
import java.net.ConnectException
import java.net.UnknownHostException

fun Throwable?.getErrorMessageResId(): Int {
    return when (this) {
        is RestErrorException -> when (this) {
            is HttpBadRequestException -> R.string.message_bad_request_error
            is HttpForbiddenException -> R.string.message_forbidden_error
            is HttpInternalServerErrorException -> R.string.message_internal_server_error
            is HttpUnauthorizedException -> R.string.message_unauthorized_error
            else -> R.string.message_unknown_error
        }
        is UnknownHostException, is ConnectException -> R.string.message_connection_error
        is HttpException -> R.string.message_response_error
        else -> when {
            this != null -> R.string.message_unknown_error
            else -> R.string.message_unknown_error
        }
    }
}

