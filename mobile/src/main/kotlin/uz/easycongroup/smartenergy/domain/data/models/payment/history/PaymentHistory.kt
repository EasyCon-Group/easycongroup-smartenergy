package uz.easycongroup.smartenergy.domain.data.models.payment.history

data class PaymentHistory(
    val day: String,
    val transactions: List<PaymentHistoryTransaction>
)