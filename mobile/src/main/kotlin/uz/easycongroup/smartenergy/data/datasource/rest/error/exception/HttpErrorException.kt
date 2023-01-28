package uz.easycongroup.smartenergy.data.datasource.rest.error.exception

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse
import java.io.IOException

open class HttpErrorException(open val response: HttpErrorResponse) : IOException()