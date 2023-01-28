package uz.easycongroup.smartenergy.domain.usecase.user

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.domain.data.repository.user.UserRepository
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase {

    override fun getUserList(): Flow<List<User>> {
        return userRepository.getUserList()
            .flowOn(Dispatchers.IO)
    }

    override fun saveSelectedUser(user: User): Flow<Unit> {
        return userRepository.saveUser(user)
            .flowOn(Dispatchers.IO)
    }

    override fun getSavedUser(): Flow<User> {
        return userRepository.getSavedUser()
            .flowOn(Dispatchers.IO)
    }

    override fun logout(): Flow<Unit> {
        return userRepository.logout()
            .flowOn(Dispatchers.IO)
    }
}