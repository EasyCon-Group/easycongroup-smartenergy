package uz.easycongroup.smartenergy.presentation.presentation.features.main.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.CatalogFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home.HomeFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile.ProfileFragment
import uz.easycongroup.smartenergy.presentation.support.cicerone.router.NestedRouter

class MainRouter constructor(
    private val globalRouter: GlobalRouter,
) : NestedRouter() {

    private var navigationListener: ((tab: MainTabs) -> Unit)? = null


    fun setNavigationListener(listener: (tab: MainTabs) -> Unit) {
        navigationListener = listener
    }

    fun openHomeTab(isNotifyRequired: Boolean = false) {
        ciceroneRouter?.replaceScreen(MainTabScreens.HomeTab)
        if (isNotifyRequired)
            navigationListener?.invoke(MainTabs.HomeTab)
    }

    fun openCategoriesTab(isNotifyRequired: Boolean = false) {
        ciceroneRouter?.replaceScreen(MainTabScreens.CategoriesTab)
        if (isNotifyRequired)
            navigationListener?.invoke(MainTabs.CatalogTab)
    }

    fun openProfileTab(isNotifyRequired: Boolean = false) {
        ciceroneRouter?.replaceScreen(MainTabScreens.ProfileTab)
        if (isNotifyRequired)
            navigationListener?.invoke(MainTabs.ProfileTab)
    }

    private object MainTabScreens {

        object HomeTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                HomeFragment.newInstance()
        }

        object CategoriesTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                CatalogFragment.newInstance()
        }

        object ProfileTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                ProfileFragment.newInstance()
        }
    }


    enum class MainTabs {
        HomeTab, CatalogTab, ProfileTab
    }
}