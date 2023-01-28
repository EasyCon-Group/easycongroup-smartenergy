package uz.easycongroup.smartenergy.core.data.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BaseResponse<T>(

    @SerialName("error")
    val error: String? = null,

    @SerialName("message")
    val message: String? = null,

    @SerialName("timestamp")
    val timestamp: String,

    @SerialName("status")
    val status: Int,

    @SerialName("data")
    val data: T
)