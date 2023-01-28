package uz.easycongroup.smartenergy.presentation.application.di.domain

import dagger.Binds
import dagger.Module
import uz.easycongroup.smartenergy.domain.usecase.common.sorttype.SortTypeUseCase
import uz.easycongroup.smartenergy.domain.usecase.common.sorttype.SortTypeUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.datepicker.DatePickerUseCase
import uz.easycongroup.smartenergy.domain.usecase.datepicker.DatePickerUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.home.HomeUseCase
import uz.easycongroup.smartenergy.domain.usecase.home.HomeUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.main.MainUseCase
import uz.easycongroup.smartenergy.domain.usecase.main.MainUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.notification.list.NotificationListUseCase
import uz.easycongroup.smartenergy.domain.usecase.notification.list.NotificationListUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.payment.PaymentHistoryUseCase
import uz.easycongroup.smartenergy.domain.usecase.payment.PaymentHistoryUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.profile.ProfileUseCase
import uz.easycongroup.smartenergy.domain.usecase.profile.ProfileUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCase
import uz.easycongroup.smartenergy.domain.usecase.state.StateUseCaseImpl
import uz.easycongroup.smartenergy.domain.usecase.user.UserUseCase
import uz.easycongroup.smartenergy.domain.usecase.user.UserUseCaseImpl

@Module(
    includes = [
        DomainDaggerModuleUseCase.Binders::class,
        DomainDaggerModuleUseCase.Providers::class
    ]
)
internal object DomainDaggerModuleUseCase {

    @Module
    interface Binders {

        @Binds
        fun bindDatePickerUseCase(
            impl: DatePickerUseCaseImpl
        ): DatePickerUseCase

        @Binds
        fun bindHomeUseCase(
            impl: HomeUseCaseImpl
        ): HomeUseCase

        @Binds
        fun bindMainUseCase(
            impl: MainUseCaseImpl
        ): MainUseCase

        @Binds
        fun bindNotificationListUseCase(
            impl: NotificationListUseCaseImpl
        ): NotificationListUseCase

        @Binds
        fun bindOrderListUseCase(
            impl: PaymentHistoryUseCaseImpl
        ): PaymentHistoryUseCase

        @Binds
        fun bindProfileUseCase(
            impl: ProfileUseCaseImpl
        ): ProfileUseCase

        @Binds
        fun bindSortTypeUseCase(
            impl: SortTypeUseCaseImpl
        ): SortTypeUseCase

        @Binds
        fun bindStateUseCase(
            impl: StateUseCaseImpl
        ): StateUseCase

        @Binds
        fun bindUserUseCase(
            impl: UserUseCaseImpl
        ): UserUseCase
    }

    @Module
    object Providers
}