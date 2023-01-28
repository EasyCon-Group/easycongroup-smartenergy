package uz.easycongroup.smartenergy.data.models.auth.check


import uz.easycongroup.smartenergy.data.models.auth.verification.VerificationType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthCheckResponse(

    @SerialName("phone")
    val phone: String? = "",

    @SerialName("code")
    val code: Int,

    @SerialName("error")
    val error: String? = "",

    @SerialName("message")
    val message: String? = "",

    @SerialName("VerificationType")
    val verificationType: VerificationType? = VerificationType.login
)