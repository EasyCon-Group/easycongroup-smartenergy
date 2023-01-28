package uz.easycongroup.smartenergy.data.repository.notification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.easycongroup.smartenergy.data.datasource.rest.service.ProfileRestService
import uz.easycongroup.smartenergy.data.mapper.notificationListResponseToNotificationList
import uz.easycongroup.smartenergy.domain.data.models.notification.NotificationList
import uz.easycongroup.smartenergy.domain.data.repository.notification.NotificationRepository
import javax.inject.Inject

internal class NotificationRepositoryImpl @Inject constructor(
    private val profileRestService: ProfileRestService
) : NotificationRepository {

    override fun getNotificationList(): Flow<NotificationList> {
        return profileRestService.getNotificationList()
            .map { it.data.notificationListResponseToNotificationList() }
    }
}