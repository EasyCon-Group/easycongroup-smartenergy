package uz.easycongroup.smartenergy.domain.data.models.notification

data class NotificationList(
    val unreadInfo: UnreadInfo,
    val notifications: List<Notification>
)