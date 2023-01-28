package uz.easycongroup.smartenergy.data.repository.payment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.easycongroup.smartenergy.data.datasource.rest.service.PaymentRestService
import uz.easycongroup.smartenergy.data.mapper.responseToPaymentHistory
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistory
import uz.easycongroup.smartenergy.domain.data.repository.payment.PaymentRepository
import javax.inject.Inject

internal class PaymentRepositoryImpl @Inject constructor(
    private val paymentRestService: PaymentRestService
) : PaymentRepository {

    override fun getPaymentHistories(page: Int): Flow<List<PaymentHistory>> {
        return paymentRestService.getPaymentHistory(page)
            .map { it -> it.data.map { it.responseToPaymentHistory() } }
    }
}