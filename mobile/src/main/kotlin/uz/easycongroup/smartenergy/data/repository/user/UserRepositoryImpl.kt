package uz.easycongroup.smartenergy.data.repository.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import uz.easycongroup.smartenergy.data.datasource.database.database.dao.UserEntityDao
import uz.easycongroup.smartenergy.data.datasource.preference.auth.AuthPreference
import uz.easycongroup.smartenergy.data.datasource.preference.user.UserPreference
import uz.easycongroup.smartenergy.data.datasource.rest.service.AuthRestService
import uz.easycongroup.smartenergy.data.datasource.rest.service.ProfileRestService
import uz.easycongroup.smartenergy.data.mapper.userEntityToUser
import uz.easycongroup.smartenergy.data.mapper.userResponseToUser
import uz.easycongroup.smartenergy.data.mapper.userToUserEntity
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.domain.data.repository.user.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val authRestService: AuthRestService,
    private val authPreference: AuthPreference,
    private val profileRestService: ProfileRestService,
    private val userEntityDao: UserEntityDao,
    private val userPreference: UserPreference
) : UserRepository {

    override fun getUserList(): Flow<List<User>> {
        return authRestService.getUserList()
            .map { response -> response.data.users.map { it.userResponseToUser() } }
    }

    override fun getSavedUserRole(): UserRole {
        return userPreference.userRole
    }

    override fun saveUser(user: User): Flow<Unit> {
        return flow { emit(userEntityDao.deleteAllUsers()) }
            .flatMapConcat {
                userPreference.apply {
                    userId = user.id
                    userRole = user.userRole
                    username = user.username
                }
                authPreference.isLoginHasBeen = true
                userEntityDao.update(user.userToUserEntity())

                flow { emit(Unit) }
            }
            .map { Unit }
    }

    override fun getSavedUser(): Flow<User> {
        return userEntityDao.getLastSignedUserEntity()
            .map { it.userEntityToUser() }
    }

    override fun logout(): Flow<Unit> {
        return flow {
            authPreference.clear()
            userPreference.clear()
            userEntityDao.deleteAllUsers()
            emit(Unit)
        }
    }
}