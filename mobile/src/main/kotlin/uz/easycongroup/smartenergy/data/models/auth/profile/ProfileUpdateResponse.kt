package uz.easycongroup.smartenergy.data.models.auth.profile


import uz.easycongroup.smartenergy.data.models.user.response.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileUpdateResponse(

    @SerialName("user")
    val user: UserResponse?
)