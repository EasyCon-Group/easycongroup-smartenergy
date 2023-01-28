package uz.easycongroup.smartenergy.presentation.application.di.presentation

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import javax.inject.Singleton

@Module
internal object ApplicationDaggerModuleManager {

    @JvmStatic
    @Provides
    @Singleton
    fun provideResourceManager(
        context: Context
    ): ResourceManager = ResourceManager(context)

}