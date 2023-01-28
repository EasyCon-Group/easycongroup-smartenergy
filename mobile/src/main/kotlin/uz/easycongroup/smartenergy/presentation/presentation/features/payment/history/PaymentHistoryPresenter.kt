package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem.HistoryDate
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem.HistoryTransaction
import uz.easycongroup.smartenergy.domain.usecase.payment.PaymentHistoryUseCase
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.router.PaymentHistoryRouter
import javax.inject.Inject

internal class PaymentHistoryPresenter @Inject constructor(
    private val useCase: PaymentHistoryUseCase,
    private val resourceManager: ResourceManager,
    private val router: PaymentHistoryRouter
) : MvpPresenter<PaymentHistoryView>() {

    override fun onFirstViewAttach() {
        getPaymentHistories()
    }

    fun getPaymentHistories() {
        useCase.getPaymentHistories()
            .onStart { viewState.onDefinedPaymentHistoryEvent(LoadingList) }
            .onEach { paymentHistories ->
                val listItems = mutableListOf<PaymentHistoryListItem>()

                paymentHistories.forEach { it ->
                    listItems.add(HistoryDate(it.day))
                    it.transactions.forEach { listItems.add(HistoryTransaction(it)) }
                }

                if (listItems.isEmpty()) viewState.onDefinedPaymentHistoryEvent(EmptyList)
                else viewState.onDefinedPaymentHistoryEvent(SuccessList(listItems))

            }
            .catch {
                Timber.wtf(it)
//                viewState.onDefinedPaymentHistoryEvent(ErrorList(it.message))
                viewState.onDefinedPaymentHistoryEvent(EmptyList)
            }
            .launchIn(presenterScope)
    }

    fun back() =
        router.back()
}