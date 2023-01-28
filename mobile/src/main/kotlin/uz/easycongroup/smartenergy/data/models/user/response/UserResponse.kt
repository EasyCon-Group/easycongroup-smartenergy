package uz.easycongroup.smartenergy.data.models.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("user_role")
    val userRole: String,

    @SerialName("info")
    val info: String,

    @SerialName("login")
    val username: String
)