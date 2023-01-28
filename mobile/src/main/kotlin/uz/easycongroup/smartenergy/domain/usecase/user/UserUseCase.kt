package uz.easycongroup.smartenergy.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.user.User

interface UserUseCase {

    fun getUserList(): Flow<List<User>>

    fun saveSelectedUser(user: User): Flow<Unit>

    fun getSavedUser(): Flow<User>

    fun logout(): Flow<Unit>
}