package uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class AuthStartRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ScreenRouter() {

    fun openMainScreen() =
        globalRouter.openMainScreen()

    fun back() =
        globalRouter.back()
}