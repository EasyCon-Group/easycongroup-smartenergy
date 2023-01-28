package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.easycongroup.smartenergy.domain.usecase.profile.ProfileUseCase
import uz.easycongroup.smartenergy.domain.usecase.user.UserUseCase
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile.router.ProfileRouter
import javax.inject.Inject

internal class ProfilePresenter @Inject constructor(
    private val router: ProfileRouter,
    private val useCase: ProfileUseCase,
    private val userUseCase: UserUseCase
) : MvpPresenter<ProfileView>() {

    fun logout() {
        userUseCase
            .logout()
            .onStart {  }
            .onEach { router.openAuthStartScreen() }
            .catch {  }
            .launchIn(presenterScope)
    }

    fun openNotificationListScreen() =
        router.openNotificationListScreen()

    fun openPaymentHistoryListScreen() =
        router.openPaymentHistoryListScreen()

    fun back() =
        router.openHomeTab()
}