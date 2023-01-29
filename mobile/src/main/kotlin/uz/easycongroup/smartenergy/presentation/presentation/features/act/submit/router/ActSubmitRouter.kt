package uz.easycongroup.smartenergy.presentation.presentation.features.act.submit.router

import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class ActSubmitRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ScreenRouter() {

    fun openActTypeSelectionScreen(
        actType: ActType?,
    ) = globalRouter.openActTypeScreen(actType)

    fun openPhotoActionSelectionScreen() =
        globalRouter.openPhotoActionSelectionScreen()

//    fun openPermissionDeniedScreen() =
//        globalRouter.openPermissionDeniedScreen()

    fun back() =
        globalRouter.back()
}