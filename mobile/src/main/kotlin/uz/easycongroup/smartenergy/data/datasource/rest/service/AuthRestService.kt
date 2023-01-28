package uz.easycongroup.smartenergy.data.datasource.rest.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.*
import uz.easycongroup.smartenergy.core.data.models.response.RootResponse
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestEndpoints
import uz.easycongroup.smartenergy.data.models.user.response.UserRootResponse

internal interface AuthRestService {

    @GET(RestEndpoints.API_GET_USER_LIST)
    fun getUserList(
    ): Flow<RootResponse<UserRootResponse>>
}