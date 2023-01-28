package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.data.models.payment.history.PaymentHistoryResponse
import uz.easycongroup.smartenergy.data.models.payment.history.PaymentHistoryTransactionResponse
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistory
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryTransaction

internal fun PaymentHistoryTransactionResponse.responseToPaymentHistoryTransaction(day: String) =
    PaymentHistoryTransaction(
        id = id,
        time = time,
        date = day,
        fullDate = "$day, $time",
        name = name,
        amount = amount
    )

internal fun PaymentHistoryResponse.responseToPaymentHistory() =
    PaymentHistory(
        day = day,
        transactions = transactions.map { it.responseToPaymentHistoryTransaction(day) }
    )