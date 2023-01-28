package uz.easycongroup.smartenergy.data.models.auth.confirmation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRootResponse(

    @SerialName("user")
    val user: LoginResponse
)