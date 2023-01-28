package uz.easycongroup.smartenergy.domain.data.models.user

import uz.easycongroup.smartenergy.data.models.user.role.UserRole

data class User(
    val id: Long,
    val fullName: String,
    val userRole: UserRole,
    val info: String,
    val username: String
)