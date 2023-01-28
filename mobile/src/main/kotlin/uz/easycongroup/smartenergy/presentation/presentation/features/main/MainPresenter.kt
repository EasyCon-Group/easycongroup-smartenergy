package uz.easycongroup.smartenergy.presentation.presentation.features.main

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.domain.usecase.main.MainUseCase
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCase
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter.MainTabs
import javax.inject.Inject
import kotlin.properties.Delegates

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: MainRouter,
    private val stateUseCase: StateUseCase,
    private val useCase: MainUseCase,
) : MvpPresenter<MainView>() {

    private var startTab: MainTabs by Delegates.notNull()

    fun setInitialStartTab(value: MainTabs) {
        startTab = value
    }

    override fun onFirstViewAttach() {
        getCartProductsTotalInfo()
        router.setNavigationListener { viewState.onTabChanged(it) }

        openHomeTab()
    }

    fun openHomeTab() = router.openHomeTab()
    fun openCatalogTab() = router.openCategoriesTab()
    fun openProfileTab() = router.openProfileTab()

    private val isLoginHasBeen: Boolean =
        stateUseCase.isLoginHasBeen()

    private fun getCartProductsTotalInfo() {
        useCase.getUnreadNotificationCount()
            .onStart { }
            .onEach { viewState.onDefinedUnreadNotificationCount(it) }
            .catch { Timber.wtf(it) }
            .launchIn(presenterScope)
    }
}