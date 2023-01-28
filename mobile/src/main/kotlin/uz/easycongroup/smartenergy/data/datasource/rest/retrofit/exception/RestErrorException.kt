package uz.easycongroup.smartenergy.data.datasource.rest.retrofit.exception

import uz.easycongroup.smartenergy.data.datasource.rest.error.response.HttpErrorResponse
import java.io.IOException

open class RestErrorException(open val response: HttpErrorResponse) : IOException()