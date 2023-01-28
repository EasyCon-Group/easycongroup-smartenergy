package uz.easycongroup.smartenergy.data.datasource.rest.error.response

interface HttpErrorResponse {
    val httpErrorCode: Int
    val errorMessage: String
}