package uz.easycongroup.smartenergy.data.models.auth.check

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthCheckRequest(
    @SerialName("phone")
    val phone: String
)