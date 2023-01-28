package uz.easycongroup.smartenergy.data.models.notification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NotificationResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("created_at")
    val date: String,

    @SerialName("note")
    val message: String,

    @SerialName("photo")
    val image: String
)