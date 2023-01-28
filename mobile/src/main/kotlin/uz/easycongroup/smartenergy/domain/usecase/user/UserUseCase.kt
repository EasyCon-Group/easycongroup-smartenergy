package uz.easycongroup.smartenergy.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.domain.data.models.user.User

interface UserUseCase {

    fun getUserList(): Flow<List<User>>

    fun saveSelectedUser(user: User): Flow<Unit>

    fun getSavedUserRole(): UserRole

    fun getSavedUser(): Flow<User>

    fun logout(): Flow<Unit>
}