package uz.easycongroup.smartenergy.presentation.presentation.features.main

import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import moxy.MvpView

interface MainView : MvpView {

    fun onDefinedUnreadNotificationCount(count: Int)

    fun onTabChanged(tab: MainRouter.MainTabs)
}