package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.data.models.notification.NotificationListResponse
import uz.easycongroup.smartenergy.data.models.notification.NotificationResponse
import uz.easycongroup.smartenergy.data.models.notification.UnreadInfoResponse
import uz.easycongroup.smartenergy.domain.data.models.notification.Notification
import uz.easycongroup.smartenergy.domain.data.models.notification.NotificationList
import uz.easycongroup.smartenergy.domain.data.models.notification.UnreadInfo

internal fun NotificationResponse.notificationResponseToNotification() =
    Notification(
        id = id,
        date = date,
        message = message,
        image = image
    )

internal fun UnreadInfoResponse.unreadInfoResponseToUnreadInfo(): UnreadInfo =
    UnreadInfo(
        count = count,
        messages = messages
    )

internal fun NotificationListResponse.notificationListResponseToNotificationList() =
    NotificationList(
        unreadInfo = unreadInfo.unreadInfoResponseToUnreadInfo(),
        notifications = notifications.map { it.notificationResponseToNotification() }
    )