package uz.easycongroup.smartenergy.data.datasource.rest.error.exception

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse

class HttpPaymentRequiredException(
    override val response: HttpErrorResponse
) : HttpErrorException(response)