package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class CatalogRouter @Inject constructor(
    private val globalRouter: GlobalRouter,
    private val mainRouter: MainRouter
) : ScreenRouter() {

    fun back() =
        mainRouter.openHomeTab(isNotifyRequired = true)
}