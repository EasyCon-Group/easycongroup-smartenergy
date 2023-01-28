package uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class NotificationListRouter @Inject constructor(
    private val globalRouter: GlobalRouter,
) : ScreenRouter() {

    fun back() =
        globalRouter.back()

}