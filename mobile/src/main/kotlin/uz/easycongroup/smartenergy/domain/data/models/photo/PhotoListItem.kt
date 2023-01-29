package uz.easycongroup.smartenergy.domain.data.models.photo

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PhotoListItem {

    data class AddPhoto(
        val isPhotoAddingAvailable: Boolean
    ) : PhotoListItem()

    @Parcelize
    data class PhotoUrl(
        val remoteUrl: String? = null,
        val localUri: Uri? = null
    ) : PhotoListItem(), Parcelable {

        fun hasImage(): Boolean =
            remoteUrl != null || localUri != null
    }
}
