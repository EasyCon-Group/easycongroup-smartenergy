package uz.easycongroup.smartenergy.domain.data.repository.common

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType

interface CommonTypesRepository {

    fun getActTypes(): Flow<List<ActType>>

    fun getPhotoActions(): Flow<List<PhotoAction>>

    fun getSortTypes(): Flow<List<SortType>>
}
