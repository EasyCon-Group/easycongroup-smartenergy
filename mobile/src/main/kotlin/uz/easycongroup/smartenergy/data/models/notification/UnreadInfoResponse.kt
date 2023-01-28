package uz.easycongroup.smartenergy.data.models.notification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UnreadInfoResponse(

    @SerialName("count")
    val count: Long,

    @SerialName("messages")
    val messages: List<String>
)