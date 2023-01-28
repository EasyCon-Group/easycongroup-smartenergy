package uz.easycongroup.smartenergy.data.models.payment.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.easycongroup.smartenergy.data.models.payment.history.PaymentHistoryTransactionResponse

@Serializable
data class PaymentHistoryResponse(

    @SerialName("date")
    val day: String,

    @SerialName("transactions")
    val transactions: List<PaymentHistoryTransactionResponse>
)