package uz.easycongroup.smartenergy.presentation.presentation.features.notification.list

import moxy.MvpView
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.domain.data.models.notification.Notification
import uz.easycongroup.smartenergy.domain.data.models.notification.UnreadInfo

internal interface NotificationListView : MvpView {

    fun onDefinedNotificationsEvent(event: LoadingListEvent<Notification>)

    fun onDefinedUnreadInfo(unreadInfo: UnreadInfo  )
}