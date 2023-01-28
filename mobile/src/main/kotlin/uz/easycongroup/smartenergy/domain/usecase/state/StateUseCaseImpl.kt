package uz.easycongroup.smartenergy.domain.usecase.state

import uz.easycongroup.smartenergy.domain.data.repository.state.StateRepository
import javax.inject.Inject

class StateUseCaseImpl @Inject constructor(
    private val stateRepository: StateRepository,
) : StateUseCase {

    override fun isLoginHasBeen(): Boolean =
        stateRepository.isLoginHasBeen()
}