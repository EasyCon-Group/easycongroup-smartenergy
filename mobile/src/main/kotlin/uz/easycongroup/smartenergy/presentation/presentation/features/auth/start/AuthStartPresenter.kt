package uz.easycongroup.smartenergy.presentation.presentation.features.auth.start

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.core.presentation.event.LoadingContentEvent.*
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.domain.usecase.user.UserUseCase
import uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.router.AuthStartRouter
import javax.inject.Inject

class AuthStartPresenter @Inject constructor(
    private val router: AuthStartRouter,
    private val useCase: UserUseCase
) : MvpPresenter<AuthStartView>() {

    override fun onFirstViewAttach() {
        getUserList()
    }

    fun getUserList() {
        useCase.getUserList()
            .onStart { viewState.onDefinedUserListEvent(LoadingList) }
            .onEach {
                if (it.isEmpty()) viewState.onDefinedUserListEvent(EmptyList)
                else viewState.onDefinedUserListEvent(SuccessList(it))
            }
            .catch {
                Timber.wtf(it)
                viewState.onDefinedUserListEvent(ErrorList(it.message))
            }
            .launchIn(presenterScope)
    }

    fun saveSelectedUser(user: User) {
        useCase.saveSelectedUser(user)
            .onStart { viewState.onDefinedUserSavingEvent(LoadingContent) }
            .onEach {
                viewState.onDefinedUserSavingEvent(SuccessContent(it))
                router.openMainScreen()
            }
            .catch { viewState.onDefinedUserSavingEvent(ErrorContent(it.message)) }
            .launchIn(presenterScope)
    }

    fun back() =
        router.back()
}