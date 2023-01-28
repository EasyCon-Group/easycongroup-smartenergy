package uz.easycongroup.smartenergy.data.models.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRootResponse(

    @SerialName("users")
    val users: List<UserResponse>
)