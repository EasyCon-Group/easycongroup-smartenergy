package uz.easycongroup.smartenergy.presentation.presentation.features.main.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter

@Module
internal object MainDaggerModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @JvmStatic
    @Provides
    @MainDaggerScope
    fun mainNavigatorHolder(
        mainRouter: MainRouter,
    ): NavigatorHolder {
        mainRouter.setCiceroneRouter(cicerone.router)
        return cicerone.navigatorHolder
    }
}