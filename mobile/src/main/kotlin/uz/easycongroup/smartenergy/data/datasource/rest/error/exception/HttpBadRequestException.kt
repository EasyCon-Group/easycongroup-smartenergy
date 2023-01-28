package uz.easycongroup.smartenergy.data.datasource.rest.error.exception

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse

class HttpBadRequestException(
    override val response: HttpErrorResponse
) : HttpErrorException(response)