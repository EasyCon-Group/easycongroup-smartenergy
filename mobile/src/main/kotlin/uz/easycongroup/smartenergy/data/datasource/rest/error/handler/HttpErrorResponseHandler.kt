package uz.easycongroup.smartenergy.data.datasource.rest.error.handler

import uz.easycongroup.smartenergy.data.datasource.rest.error.dispatcher.HttpErrorDispatcherComponent
import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse
import java.io.IOException

internal interface HttpErrorResponseHandler : HttpErrorDispatcherComponent {

    fun onHandleError(
        httpErrorCode: Int,
        errorResponse: HttpErrorResponse
    ): IOException?
}