package uz.easycongroup.smartenergy.domain.usecase.act

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.PhotoUrl

interface ActSubmitUseCase {

    val isPhotoAddingAvailable: Boolean
    fun getPhotoListItems(): List<PhotoListItem>
    fun setImageUriFromCamera(uri: Uri)
    fun setImageUriFromGallery(uri: Uri)
    fun deletePhotoFromPhotoList(photoUrl: PhotoUrl)

    fun setEnteredTitle(value: String)
    fun getEnteredTitle(): String

    fun getSelectedActType(): ActType?
    fun setSelectedActType(value: ActType)

    fun setEnteredDescription(value: String)
    fun getEnteredDescription(): String

    fun submitAct(): Flow<Unit>
}