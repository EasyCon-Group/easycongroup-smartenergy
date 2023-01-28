package uz.easycongroup.smartenergy.data.datasource.rest.error.response

class DefaultHttpErrorResponse(
    override val httpErrorCode: Int,
    override val errorMessage: String
): HttpErrorResponse