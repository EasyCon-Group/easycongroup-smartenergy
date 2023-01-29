package uz.easycongroup.smartenergy.domain.usecase.common.photoaction

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import uz.easycongroup.smartenergy.domain.sharedflow.global.photoaction.PhotoActionSharedFlow
import javax.inject.Inject

class PhotoActionUseCaseImpl @Inject constructor(
    private val commonTypesRepository: CommonTypesRepository,
    private val photoActionSharedFlow: PhotoActionSharedFlow
) : PhotoActionUseCase {

    private var selectedItem: PhotoAction? = null

    override fun getAvailableItems(): Flow<List<PhotoAction>> {
        return commonTypesRepository.getPhotoActions()
            .flowOn(Dispatchers.IO)
    }

    override fun setSelectedItem(value: PhotoAction) {
        selectedItem = value
    }

    override fun returnSelectedItem() {
        if (selectedItem != null)
            photoActionSharedFlow.tryEmit(selectedItem!!)
    }
}