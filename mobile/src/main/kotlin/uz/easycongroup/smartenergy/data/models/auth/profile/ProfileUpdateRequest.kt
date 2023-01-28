package uz.easycongroup.smartenergy.data.models.auth.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileUpdateRequest(
    @SerialName("fullName")
    val fullName: String
)