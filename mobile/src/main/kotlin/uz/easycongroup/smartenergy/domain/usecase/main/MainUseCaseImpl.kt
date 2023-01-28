package uz.easycongroup.smartenergy.domain.usecase.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
) : MainUseCase {

    override fun getUnreadNotificationCount(): Flow<Int> {
        return flowOf(0)
            .flowOn(Dispatchers.IO)
    }
}