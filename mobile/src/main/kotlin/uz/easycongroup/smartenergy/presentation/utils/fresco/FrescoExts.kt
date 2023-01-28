package uz.easycongroup.smartenergy.presentation.utils.fresco

import com.facebook.drawee.view.SimpleDraweeView
import uz.easycongroup.smartenergy.BuildConfig

internal fun SimpleDraweeView.setimageUrlOrId(imageId: Long) {
    setimageUrlOrId(imageId.toString())
}

internal fun SimpleDraweeView.setimageUrlOrId(imageId: String) {
    if (imageId.contains("http", true)) setImageURI(imageId)
    else setImageURI("${BuildConfig.BASE_URL}$imageId")
}