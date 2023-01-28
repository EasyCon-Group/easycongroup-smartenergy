package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history

import moxy.MvpView
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem

internal interface PaymentHistoryView : MvpView {

    fun onDefinedPaymentHistoryEvent(event: LoadingListEvent<PaymentHistoryListItem>)
}