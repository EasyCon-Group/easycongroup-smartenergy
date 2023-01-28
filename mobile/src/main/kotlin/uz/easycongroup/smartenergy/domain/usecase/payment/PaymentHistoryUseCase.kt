package uz.easycongroup.smartenergy.domain.usecase.payment

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistory

interface PaymentHistoryUseCase {

    fun getPaymentHistories(): Flow<List<PaymentHistory>>
}