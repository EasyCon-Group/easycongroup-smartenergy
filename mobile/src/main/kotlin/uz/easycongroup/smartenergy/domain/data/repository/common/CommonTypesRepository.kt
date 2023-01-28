package uz.easycongroup.smartenergy.domain.data.repository.common

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType

interface CommonTypesRepository {

    fun getSortTypes(): Flow<List<SortType>>
}
