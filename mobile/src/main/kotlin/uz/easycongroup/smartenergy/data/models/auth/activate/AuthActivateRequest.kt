package uz.easycongroup.smartenergy.data.models.auth.activate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthActivateRequest(

    @SerialName("phone")
    val phone: String,

    @SerialName("code")
    val code: String,

    @SerialName("verification_type")
    val verificationType: String,
)