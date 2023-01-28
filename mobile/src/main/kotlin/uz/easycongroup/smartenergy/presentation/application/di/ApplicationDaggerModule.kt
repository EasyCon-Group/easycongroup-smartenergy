package uz.easycongroup.smartenergy.presentation.application.di

import dagger.Module
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleDao
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModulePreference
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRepository
import uz.easycongroup.smartenergy.presentation.application.di.data.DataDaggerModuleRest
import uz.easycongroup.smartenergy.presentation.application.di.domain.DomainDaggerModuleSharedFlow
import uz.easycongroup.smartenergy.presentation.application.di.domain.DomainDaggerModuleUseCase
import uz.easycongroup.smartenergy.presentation.application.di.presentation.ApplicationDaggerModuleManager

@Module(
    includes = [
        ApplicationDaggerModuleManager::class,
        DataDaggerModuleDao::class,
        DataDaggerModulePreference::class,
        DataDaggerModuleRepository::class,
        DataDaggerModuleRest::class,
        DomainDaggerModuleSharedFlow::class,
        DomainDaggerModuleUseCase::class,
    ]
)
object ApplicationDaggerModule