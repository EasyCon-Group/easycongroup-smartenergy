package uz.easycongroup.smartenergy.presentation.global.di

import dagger.Subcomponent
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.presentation.common.features.datepicker.bottomsheet.DatePickerFragment
import uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype.SortTypeFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.AuthStartFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.di.MainDaggerComponent
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.CatalogFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home.HomeFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile.ProfileFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.NotificationListFragment
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.PaymentHistoryFragment

@GlobalScope
@Subcomponent(
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModuleNavigation::class,
    ]
)
internal interface GlobalDaggerComponent {

    val mainDaggerComponent: MainDaggerComponent.Factory

    val globalRouter: GlobalRouter
    val mainRouter: MainRouter

    fun inject(activity: GlobalActivity)

    fun inject(fragment: AuthStartFragment)
    fun inject(fragment: CatalogFragment)
    fun inject(fragment: DatePickerFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: NotificationListFragment)
    fun inject(fragment: PaymentHistoryFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SortTypeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): GlobalDaggerComponent
    }
}