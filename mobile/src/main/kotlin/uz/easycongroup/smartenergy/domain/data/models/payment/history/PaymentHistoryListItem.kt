package uz.easycongroup.smartenergy.domain.data.models.payment.history

sealed class PaymentHistoryListItem {

    data class HistoryDate(
        val date: String
    ) : PaymentHistoryListItem()

    data class HistoryTransaction(
        val transaction: PaymentHistoryTransaction
    ) : PaymentHistoryListItem()
}
