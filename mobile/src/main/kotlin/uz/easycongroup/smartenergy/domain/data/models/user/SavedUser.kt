package uz.easycongroup.smartenergy.domain.data.models.user

import uz.easycongroup.smartenergy.data.models.auth.user.UserType

data class SavedUser(
    val userId: Long,
    val username: String,
    val phone: String,
    val userType: UserType,
    val regionId: Long,
    val regionName: String,
    val firstName: String,
    val lastName: String
) {
    val fullName: String
        get() = "$firstName $lastName"

    val isAgent: Boolean
        get() = userType == UserType.agent

    val isNotAgent: Boolean
        get() = userType != UserType.agent
}