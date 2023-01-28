package uz.easycongroup.smartenergy.domain.usecase.payment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistory
import uz.easycongroup.smartenergy.domain.data.repository.payment.PaymentRepository
import javax.inject.Inject

class PaymentHistoryUseCaseImpl @Inject constructor(
    private val paymentRepository: PaymentRepository,
) : PaymentHistoryUseCase {

    private var page = 1

    override fun getPaymentHistories(): Flow<List<PaymentHistory>> {
        return paymentRepository.getPaymentHistories(page)
            .flowOn(Dispatchers.IO)
    }
}