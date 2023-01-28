package uz.easycongroup.smartenergy.presentation.presentation.features.main.di

import dagger.Subcomponent
import uz.easycongroup.smartenergy.presentation.presentation.features.main.MainFragment

@MainDaggerScope
@Subcomponent(modules = [MainDaggerModule::class])
internal interface MainDaggerComponent {

    fun inject(fragment: MainFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainDaggerComponent
    }
}