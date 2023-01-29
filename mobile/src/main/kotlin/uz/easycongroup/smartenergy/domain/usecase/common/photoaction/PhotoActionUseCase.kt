package uz.easycongroup.smartenergy.domain.usecase.common.photoaction

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction

interface PhotoActionUseCase {

    fun getAvailableItems(): Flow<List<PhotoAction>>

    fun setSelectedItem(value: PhotoAction)

    fun returnSelectedItem()
}