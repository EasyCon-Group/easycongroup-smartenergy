package uz.easycongroup.smartenergy.data.models.order

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
internal data class OrderResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("title")
    val title: String,

    @SerialName("status")
    val status: String,

    @SerialName("price")
    val price: String,

    @SerialName("currency")
    val currency: String,

    @SerialName("date")
    val date: String,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("isDeliveryFeedbackRequired")
    val isDeliveryFeedbackRequired: Boolean
)