package uz.easycongroup.smartenergy.presentation.global.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import uz.easycongroup.smartenergy.presentation.global.di.GlobalDaggerModuleNavigation.GlobalRouterProviders
import uz.easycongroup.smartenergy.presentation.global.di.GlobalDaggerModuleNavigation.NestedRouterProviders
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter

@Module(
    includes = [
        GlobalRouterProviders::class,
        NestedRouterProviders::class
    ]
)
object GlobalDaggerModuleNavigation {

    private val globalCicerone: Cicerone<GlobalRouter> = Cicerone.create(GlobalRouter())

    @Module
    object GlobalRouterProviders {

        @JvmStatic
        @Provides
        @GlobalScope
        fun globalRouter(): GlobalRouter =
            globalCicerone.router

        @JvmStatic
        @Provides
        fun globalCicerone(): Cicerone<GlobalRouter> =
            globalCicerone
    }

    @Module
    object NestedRouterProviders {

        @JvmStatic
        @Provides
        @GlobalScope
        fun mainRouter(
            globalRouter: GlobalRouter
        ): MainRouter =
            MainRouter(globalRouter)
    }
}