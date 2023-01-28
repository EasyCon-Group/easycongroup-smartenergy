package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class ProfileRouter @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val mainRouter: MainRouter
) : ScreenRouter() {

    fun openHomeTab() =
        mainRouter.openHomeTab(true)

    fun openMainScreen() =
        globalRouter.openMainScreen()

    fun openNotificationListScreen() =
        globalRouter.openNotificationListScreen()

    fun openPaymentHistoryListScreen() =
        globalRouter.openOrderListScreen()

    fun openAuthStartScreen()  =
        globalRouter.openAuthStartScreen(true)

    fun back() =
        openHomeTab()
}