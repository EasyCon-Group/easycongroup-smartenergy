package uz.easycongroup.smartenergy.data.datasource.rest.service

import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

internal interface ActRestService {

    @POST(API_POST_SUBMIT_ACT)
    fun submitAct(
        @Body body: RequestBody
    ): Flow<Unit>

    companion object {
        const val API_POST_SUBMIT_ACT = "act/submit"
    }
}