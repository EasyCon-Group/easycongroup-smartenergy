package uz.easycongroup.smartenergy.presentation.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.easycongroup.smartenergy.presentation.application.Application
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import uz.easycongroup.smartenergy.presentation.global.di.GlobalDaggerComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDaggerModule::class])
internal interface ApplicationDaggerComponent {

    val resourceManager: ResourceManager

    val globalDaggerComponent: GlobalDaggerComponent.Factory

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationDaggerComponent
    }

    companion object {
        fun create(context: Context): ApplicationDaggerComponent =
            DaggerApplicationDaggerComponent.factory()
                .create(context)
    }
}