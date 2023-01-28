package uz.easycongroup.smartenergy.presentation.application.di.data

import dagger.Binds
import dagger.Module
import uz.easycongroup.smartenergy.data.repository.auth.AuthRepositoryImpl
import uz.easycongroup.smartenergy.data.repository.common.CommonTypesRepositoryImpl
import uz.easycongroup.smartenergy.data.repository.notification.NotificationRepositoryImpl
import uz.easycongroup.smartenergy.data.repository.payment.PaymentRepositoryImpl
import uz.easycongroup.smartenergy.data.repository.state.StateRepositoryImpl
import uz.easycongroup.smartenergy.data.repository.user.UserRepositoryImpl
import uz.easycongroup.smartenergy.domain.data.repository.auth.AuthRepository
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import uz.easycongroup.smartenergy.domain.data.repository.notification.NotificationRepository
import uz.easycongroup.smartenergy.domain.data.repository.payment.PaymentRepository
import uz.easycongroup.smartenergy.domain.data.repository.state.StateRepository
import uz.easycongroup.smartenergy.domain.data.repository.user.UserRepository

@Module(includes = [DataDaggerModuleRepository.Binders::class])
internal object DataDaggerModuleRepository {

    @Module
    interface Binders {

        @Binds
        fun bindAuthRepository(
            impl: AuthRepositoryImpl
        ): AuthRepository

        @Binds
        fun bindCommonTypesRepository(
            impl: CommonTypesRepositoryImpl
        ): CommonTypesRepository

        @Binds
        fun bindNotificationRepository(
            impl: NotificationRepositoryImpl
        ): NotificationRepository

        @Binds
        fun bindPaymentRepository(
            impl: PaymentRepositoryImpl
        ): PaymentRepository

        @Binds
        fun bindStateRepository(
            impl: StateRepositoryImpl
        ): StateRepository

        @Binds
        fun bindUserRepository(
            impl: UserRepositoryImpl
        ): UserRepository
    }
}