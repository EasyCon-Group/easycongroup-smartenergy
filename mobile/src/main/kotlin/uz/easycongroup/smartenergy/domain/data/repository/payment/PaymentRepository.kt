package uz.easycongroup.smartenergy.domain.data.repository.payment

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistory

interface PaymentRepository {

    fun getPaymentHistories(page: Int): Flow<List<PaymentHistory>>
}