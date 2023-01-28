package uz.easycongroup.smartenergy.data.models.auth.confirmation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class LoginResponse(

    @SerialName("token")
    val token: String,

    @SerialName("projectId")
    val projectId: Int,

    @SerialName("user")
    val user: LoginUserResponse
)