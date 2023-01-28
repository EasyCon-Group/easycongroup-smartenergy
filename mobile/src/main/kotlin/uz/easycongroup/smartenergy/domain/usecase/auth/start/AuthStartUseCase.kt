package uz.easycongroup.smartenergy.domain.usecase.auth.start

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.user.User

interface AuthStartUseCase {

    fun getUserList(): Flow<List<User>>

    fun saveUser(user: User): Flow<Unit>
}