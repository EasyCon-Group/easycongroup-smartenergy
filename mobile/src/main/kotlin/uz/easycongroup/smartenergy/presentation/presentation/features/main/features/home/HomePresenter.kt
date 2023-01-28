package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home

import moxy.MvpPresenter
import uz.easycongroup.smartenergy.data.models.user.role.UserRole
import uz.easycongroup.smartenergy.data.models.user.role.UserRole.*
import uz.easycongroup.smartenergy.domain.usecase.home.HomeUseCase
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCase
import uz.easycongroup.smartenergy.domain.usecase.user.UserUseCase
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home.router.HomeRouter
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val resourceManager: ResourceManager,
    private val router: HomeRouter,
    private val stateUseCase: StateUseCase,
    private val useCase: HomeUseCase,
    private val userUseCase: UserUseCase
) : MvpPresenter<HomeView>() {

    override fun onFirstViewAttach() {
        val userRole = userUseCase.getSavedUserRole()
        viewState.onDefinedViewVisibility(
            isManagerViewsVisible = userRole.isManager,
            isSupervisorViewsVisible = userRole.isSupervisor,
            isCitizenViewsVisible = userRole.isCitizen
        )
    }


}