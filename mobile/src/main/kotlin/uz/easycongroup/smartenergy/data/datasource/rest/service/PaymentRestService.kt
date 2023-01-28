package uz.easycongroup.smartenergy.data.datasource.rest.service

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import uz.easycongroup.smartenergy.core.data.models.response.RootResponse
import uz.easycongroup.smartenergy.data.models.payment.history.PaymentHistoryResponse

internal interface PaymentRestService {

    @GET(API_GET_PAYMENT_HISTORY)
    fun getPaymentHistory(
        @Query("page") page: Int,
    ): Flow<RootResponse<List<PaymentHistoryResponse>>>

    private companion object {

        const val API_GET_PAYMENT_HISTORY: String = "transactions"
    }
}