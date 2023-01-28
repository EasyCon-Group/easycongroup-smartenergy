package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class HomeRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ScreenRouter() {

    fun openNotificationListScreen() =
        globalRouter.openNotificationListScreen()
}