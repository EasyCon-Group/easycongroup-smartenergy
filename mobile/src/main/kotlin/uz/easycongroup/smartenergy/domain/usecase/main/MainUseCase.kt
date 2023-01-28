package uz.easycongroup.smartenergy.domain.usecase.main

import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    fun getUnreadNotificationCount(): Flow<Int>
}