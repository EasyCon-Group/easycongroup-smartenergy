package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.data.models.user.entity.UserEntity
import uz.easycongroup.smartenergy.data.models.user.response.UserResponse
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.domain.data.models.user.User

internal fun UserResponse.userResponseToUserEntity(): UserEntity =
    UserEntity(
        id = id,
        fullName = fullName,
        userRole = UserRole.valueOrDefault(userRole),
        info = info,
        username = username
    )

internal fun UserResponse.userResponseToUser(): User =
    User(
        id = id,
        fullName = fullName,
        userRole = UserRole.valueOrDefault(userRole),
        info = info,
        username = username
    )

internal fun UserEntity.userEntityToUser(): User =
    User(
        id = id,
        fullName = fullName,
        userRole = userRole,
        info = info,
        username = username
    )

internal fun User.userToUserEntity(): UserEntity =
    UserEntity(
        id = id,
        fullName = fullName,
        userRole = userRole,
        info = info,
        username = username
    )