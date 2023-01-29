package uz.easycongroup.smartenergy.data.repository.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType.*
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction.*
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import javax.inject.Inject

class CommonTypesRepositoryImpl @Inject constructor(

) : CommonTypesRepository {

    override fun getActTypes(): Flow<List<ActType>> {
        return flowOf(listOf(SET_COUNTER, REPLACE_COUNTER, ILLEGAL_USAGE))
    }

    override fun getPhotoActions(): Flow<List<PhotoAction>> {
        return flowOf(listOf(ACTION_ADD_FROM_CAMERA, ACTION_ADD_FROM_GALLERY))
    }

    override fun getSortTypes(): Flow<List<SortType>> {
        return flowOf(SortType.values().toList())
    }
}