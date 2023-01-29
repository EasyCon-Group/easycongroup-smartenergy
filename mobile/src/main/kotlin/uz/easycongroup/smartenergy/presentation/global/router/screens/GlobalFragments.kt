package uz.easycongroup.smartenergy.presentation.global.router.screens

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.easycongroup.smartenergy.presentation.presentation.features.act.submit.ActSubmitFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.AuthStartFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.MainFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.NotificationListFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.PaymentHistoryFragment

object GlobalFragments {

    object ActSubmitScreen : SupportAppScreen() {
        override fun getFragment(): Fragment =
            ActSubmitFragment.newInstance()
    }

    object AuthStartScreen : SupportAppScreen() {
        override fun getFragment(): Fragment =
            AuthStartFragment.newInstance()
    }

    data class MainScreen(val tab: MainRouter.MainTabs) : SupportAppScreen() {
        override fun getFragment(): Fragment =
            MainFragment.newInstance(tab)
    }

    object NotificationListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment =
            NotificationListFragment.newInstance()
    }

    object OrderList : SupportAppScreen() {
        override fun getFragment(): Fragment =
            PaymentHistoryFragment.newInstance()
    }
}