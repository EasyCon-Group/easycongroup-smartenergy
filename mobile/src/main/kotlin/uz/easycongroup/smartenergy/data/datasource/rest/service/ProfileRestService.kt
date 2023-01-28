package uz.easycongroup.smartenergy.data.datasource.rest.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.easycongroup.smartenergy.core.data.models.response.RootResponse
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestEndpoints.API_GET_USER_NOTIFICATIONS
import uz.easycongroup.smartenergy.data.models.notification.NotificationListResponse

internal interface ProfileRestService {

    @GET(API_GET_USER_NOTIFICATIONS)
    fun getNotificationList(
    ): Flow<RootResponse<NotificationListResponse>>
}