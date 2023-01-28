package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.router

import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.ScreenRouter
import javax.inject.Inject

class PaymentHistoryRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : ScreenRouter() {

    fun back() =
        globalRouter.back()
}