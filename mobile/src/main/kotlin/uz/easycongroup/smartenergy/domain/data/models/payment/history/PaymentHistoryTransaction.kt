package uz.easycongroup.smartenergy.domain.data.models.payment.history

data class PaymentHistoryTransaction(
    val id: Long,
    val time: String,
    val date: String,
    val fullDate: String,
    val name: String,
    val amount: Double
)