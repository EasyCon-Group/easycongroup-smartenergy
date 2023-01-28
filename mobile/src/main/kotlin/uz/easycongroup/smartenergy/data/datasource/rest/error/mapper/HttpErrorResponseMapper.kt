package uz.easycongroup.smartenergy.data.datasource.rest.error.mapper

import uz.easycongroup.smartenergy.data.datasource.rest.error.dispatcher.HttpErrorDispatcherComponent
import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse

internal interface HttpErrorResponseMapper : HttpErrorDispatcherComponent {

    fun onHandleResponse(
        httpErrorCode: Int,
        response: String?
    ): HttpErrorResponse?
}