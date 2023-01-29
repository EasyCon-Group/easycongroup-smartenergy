package uz.easycongroup.smartenergy.presentation.utils.fresco

import com.facebook.drawee.view.SimpleDraweeView
import uz.easycongroup.smartenergy.BuildConfig
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem

internal fun SimpleDraweeView.setImageUrlOrId(imageId: Long) {
    setImageUrlOrId(imageId.toString())
}

internal fun SimpleDraweeView.setImageUrlOrId(imageId: String) {
    if (imageId.contains("http", true)) setImageURI(imageId)
    else setImageURI("${BuildConfig.BASE_URL}$imageId")
}

internal fun SimpleDraweeView.setImageURI(url: PhotoListItem.PhotoUrl?) {
    if (url == null || !url.hasImage()) {
        setImageURI("")
        return
    }

    if (url.remoteUrl != null) setImageURI(url.remoteUrl)

    if (url.localUri != null) setImageURI(url.localUri, context)
}