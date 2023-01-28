package uz.easycongroup.smartenergy.domain.data.repository.notification

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.notification.NotificationList

interface NotificationRepository {

    fun getNotificationList(): Flow<NotificationList>
}