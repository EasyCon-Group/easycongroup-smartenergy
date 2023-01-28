package uz.easycongroup.smartenergy.domain.usecase.notification.list

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.notification.NotificationList
import uz.easycongroup.smartenergy.domain.data.models.notification.UnreadInfo
import uz.easycongroup.smartenergy.domain.data.repository.notification.NotificationRepository
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCase
import javax.inject.Inject

class NotificationListUseCaseImpl @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val stateUseCase: StateUseCase
) : NotificationListUseCase {

    override fun getNotificationList(): Flow<NotificationList> {
        return when {
            stateUseCase.isLoginHasBeen() -> notificationRepository.getNotificationList()
            else -> flowOf(NotificationList(UnreadInfo(0, listOf()), listOf()))
        }
            .flowOn(Dispatchers.IO)
    }
}