package uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction

import moxy.MvpView
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction

interface PhotoActionView : MvpView {

    fun onDefinedItems(items: List<PhotoAction>)

    fun onDismissDialog()
}