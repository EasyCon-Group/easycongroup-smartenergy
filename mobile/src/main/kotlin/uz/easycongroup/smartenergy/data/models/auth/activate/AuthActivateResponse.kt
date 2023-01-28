package uz.easycongroup.smartenergy.data.models.auth.activate


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthActivateResponse(

    @SerialName("access")
    val accessToken: String,

    @SerialName("refresh")
    val refreshToken: String
)