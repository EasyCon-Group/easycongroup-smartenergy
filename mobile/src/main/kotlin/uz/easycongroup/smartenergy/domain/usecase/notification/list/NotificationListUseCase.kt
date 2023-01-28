package uz.easycongroup.smartenergy.domain.usecase.notification.list

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.notification.NotificationList

interface NotificationListUseCase {

    fun getNotificationList(): Flow<NotificationList>
}