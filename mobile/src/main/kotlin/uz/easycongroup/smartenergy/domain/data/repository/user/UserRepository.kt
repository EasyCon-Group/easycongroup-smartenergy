package uz.easycongroup.smartenergy.domain.data.repository.user

import uz.easycongroup.smartenergy.domain.data.models.user.User
import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.data.models.auth.user.UserType
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.domain.data.models.user.SavedUser

interface UserRepository {

    fun getUserList(): Flow<List<User>>

    fun getSavedUserRole(): UserRole

    fun getSavedUser(): Flow<User>

     fun saveUser(user: User): Flow<Unit>

    fun logout(): Flow<Unit>
}