package uz.easycongroup.smartenergy.data.models.notification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NotificationListResponse(

    @SerialName("unreads")
    val unreadInfo: UnreadInfoResponse,

    @SerialName("reads")
    val notifications: List<NotificationResponse>
)