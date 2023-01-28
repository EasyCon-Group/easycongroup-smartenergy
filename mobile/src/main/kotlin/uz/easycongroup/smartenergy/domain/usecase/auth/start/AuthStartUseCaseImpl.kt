package uz.easycongroup.smartenergy.domain.usecase.auth.start

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.domain.data.repository.user.UserRepository
import javax.inject.Inject

class AuthStartUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : AuthStartUseCase {

    override fun getUserList(): Flow<List<User>> {
        return userRepository.getUserList()
            .flowOn(Dispatchers.IO)
    }

    override fun saveUser(user: User): Flow<Unit> {
        return userRepository.saveUser(user)
            .flowOn(Dispatchers.IO)
    }
}