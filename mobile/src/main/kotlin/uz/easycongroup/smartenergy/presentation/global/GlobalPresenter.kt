package uz.easycongroup.smartenergy.presentation.global

import moxy.MvpPresenter
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCase
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import javax.inject.Inject

class GlobalPresenter @Inject constructor(
    private val router: GlobalRouter,
    private val stateUseCase: StateUseCase,
) : MvpPresenter<GlobalView>() {

    override fun onFirstViewAttach() {
        when {
            !stateUseCase.isLoginHasBeen() -> router.openAuthStartScreen(true)
            else -> router.openMainScreen()
        }
    }

    private fun openAuthStartScreen() =
        router.openAuthStartScreen(true)
}