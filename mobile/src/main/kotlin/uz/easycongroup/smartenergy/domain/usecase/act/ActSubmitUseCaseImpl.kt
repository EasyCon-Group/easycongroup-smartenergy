package uz.easycongroup.smartenergy.domain.usecase.act

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.easycongroup.smartenergy.domain.data.models.act.creation.ActSubmitParams
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.repository.act.ActRepository
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.*
import uz.easycongroup.smartenergy.domain.usecase.act.exception.ActSubmitException
import javax.inject.Inject

class ActSubmitUseCaseImpl @Inject constructor(
    private val actRepository: ActRepository
) : ActSubmitUseCase {

    private val newImages: MutableList<PhotoListItem> = mutableListOf()

    private var title: String? = null
    private var actType: ActType? = null
    private var desc: String? = null

    override val isPhotoAddingAvailable: Boolean
        get() = newImages.size < 4

    override fun getPhotoListItems(): List<PhotoListItem> =
        mutableListOf<PhotoListItem>(AddPhoto(isPhotoAddingAvailable)).apply {
                addAll(newImages)
            }

    override fun setImageUriFromCamera(uri: Uri) {
        newImages.add(PhotoUrl(localUri = uri))
    }

    override fun setImageUriFromGallery(uri: Uri) {
        newImages.add(PhotoUrl(localUri = uri))
    }

    override fun deletePhotoFromPhotoList(photoUrl: PhotoUrl) {
        newImages.remove(photoUrl)
    }

    override fun setEnteredTitle(value: String) {
        title = value.trim()
    }

    override fun getEnteredTitle(): String = title ?: ""

    override fun setSelectedActType(value: ActType) {
        actType = value
    }

    override fun getSelectedActType(): ActType? = actType

    override fun setEnteredDescription(value: String) {
        desc = value.trim()
    }

    override fun getEnteredDescription(): String = desc ?: ""

    override fun submitAct(): Flow<Unit> {
        val exception = checkAndGetException()
        return when {
            exception.isPassed -> flow { throw exception }
            else -> actRepository.submitAct(ActSubmitParams(actType = actType!!,
                    title = title!!,
                    description = desc!!,
                    images = newImages.filterIsInstance(PhotoUrl::class.java)
                        .mapNotNull { it.localUri }))
        }
            .flowOn(Dispatchers.IO)
    }

    private fun checkAndGetException()= ActSubmitException(
        isTitleEmpty = title.isNullOrEmpty(),
        isActTypeNotSelected = actType == null,
        isDescriptionEmpty = desc.isNullOrEmpty(),
        isPhoneNotAdded = newImages.isEmpty()
    )
}