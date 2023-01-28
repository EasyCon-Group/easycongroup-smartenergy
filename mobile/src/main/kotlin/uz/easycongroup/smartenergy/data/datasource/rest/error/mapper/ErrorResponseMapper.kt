package uz.easycongroup.smartenergy.data.datasource.rest.error.mapper

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse

internal class ErrorResponseMapper : HttpErrorResponseMapper {

    override fun onHandleResponse(httpErrorCode: Int, response: String?): HttpErrorResponse? =
        null
}