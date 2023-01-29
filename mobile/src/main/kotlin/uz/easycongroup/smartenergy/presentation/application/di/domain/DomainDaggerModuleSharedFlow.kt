package uz.easycongroup.smartenergy.presentation.application.di.domain

import dagger.Module
import dagger.Provides
import uz.easycongroup.smartenergy.domain.sharedflow.global.acttype.ActTypeSharedFlow
import uz.easycongroup.smartenergy.domain.sharedflow.global.date.DateSelectionSharedFlow
import uz.easycongroup.smartenergy.domain.sharedflow.global.photoaction.PhotoActionSharedFlow
import uz.easycongroup.smartenergy.domain.sharedflow.global.sorttype.SortTypeSharedFlow
import uz.easycongroup.smartenergy.presentation.application.di.domain.DomainDaggerModuleSharedFlow.GlobalSharedFlowProviders
import uz.easycongroup.smartenergy.presentation.application.di.domain.DomainDaggerModuleSharedFlow.InternalSharedFlowProviders
import javax.inject.Singleton

@Module(
    includes = [
        GlobalSharedFlowProviders::class,
        InternalSharedFlowProviders::class
    ]
)
internal object DomainDaggerModuleSharedFlow {

    @Module
    object GlobalSharedFlowProviders {

        @JvmStatic
        @Provides
        @Singleton
        fun provideActTypeSharedFlow(
        ): ActTypeSharedFlow = ActTypeSharedFlow()

        @JvmStatic
        @Provides
        @Singleton
        fun provideDateSelectionSharedFlow(
        ): DateSelectionSharedFlow = DateSelectionSharedFlow()

        @JvmStatic
        @Provides
        @Singleton
        fun providePhotoActionSharedFlow(
        ): PhotoActionSharedFlow = PhotoActionSharedFlow()

        @JvmStatic
        @Provides
        @Singleton
        fun provideSortTypeSharedFlow(
        ): SortTypeSharedFlow = SortTypeSharedFlow()
    }

    @Module
    object InternalSharedFlowProviders {

    }
}