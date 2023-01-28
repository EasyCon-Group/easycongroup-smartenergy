package uz.easycongroup.smartenergy.presentation.presentation.features.auth.start

import moxy.MvpView
import uz.easycongroup.smartenergy.core.presentation.event.LoadingContentEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.domain.data.models.user.User

interface AuthStartView : MvpView {

    fun onDefinedUserListEvent(event: LoadingListEvent<User>)

    fun onDefinedUserSavingEvent(event: LoadingContentEvent<Unit>)
}