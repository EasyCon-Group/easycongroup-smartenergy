package uz.easycongroup.smartenergy.data.models.payment.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentHistoryTransactionResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("created_at")
    val time: String,

    @SerialName("name_uz")
    val name: String,

    @SerialName("amount")
    val amount: Double
)