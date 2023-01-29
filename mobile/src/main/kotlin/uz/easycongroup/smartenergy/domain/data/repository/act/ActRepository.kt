package uz.easycongroup.smartenergy.domain.data.repository.act

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.act.creation.ActSubmitParams

interface ActRepository {

    fun submitAct(params: ActSubmitParams): Flow<Unit>
}