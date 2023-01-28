package uz.easycongroup.smartenergy.data.repository.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import javax.inject.Inject

class CommonTypesRepositoryImpl @Inject constructor(

) : CommonTypesRepository {

    override fun getSortTypes(): Flow<List<SortType>> {
        return flowOf(SortType.values().toList())
    }
}