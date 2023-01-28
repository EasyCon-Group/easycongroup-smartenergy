package uz.easycongroup.smartenergy.presentation.presentation.features.notification.list

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.domain.usecase.notification.list.NotificationListUseCase
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.router.NotificationListRouter
import javax.inject.Inject

internal class NotificationListPresenter @Inject constructor(
    private val resourceManager: ResourceManager,
    private val router: NotificationListRouter,
    private val useCase: NotificationListUseCase
) : MvpPresenter<NotificationListView>() {

    override fun onFirstViewAttach() {
        getNotificationList()
    }

    fun getNotificationList() {
        useCase.getNotificationList()
            .onStart { viewState.onDefinedNotificationsEvent(LoadingList) }
            .onEach {
                if (it.notifications.isEmpty()) viewState.onDefinedNotificationsEvent(EmptyList)
                else viewState.onDefinedNotificationsEvent(SuccessList(it.notifications))
            }
            .catch {
                Timber.wtf(it)
//                viewState.onDefinedNotificationsEvent(ErrorList(resourceManager.get(it)))
                viewState.onDefinedNotificationsEvent(EmptyList)
            }
            .launchIn(presenterScope)
    }

    fun back() =
        router.back()
}