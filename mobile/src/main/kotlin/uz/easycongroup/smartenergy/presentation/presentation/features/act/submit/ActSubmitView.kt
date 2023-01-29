package uz.easycongroup.smartenergy.presentation.presentation.features.act.submit

import moxy.MvpView
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem

interface ActSubmitView : MvpView {

    fun onDefinedActType(actType: ActType)

    fun onDefinedPhotoAddingNotAvailable()
    fun onDefinedPhotoItems(items: List<PhotoListItem>)
    fun onOpenCamera()
    fun onOpenGallery()

    fun onLoadingPortfolioCreation()
    fun onSuccessPortfolioCreation()
    fun onFailurePortfolioCreation()
    fun onFailurePortfolioCreationHideProgress()

    fun onActTypeNotSelected()
    fun onTitleNotValid()
    fun onDescNotValid()
    fun onPhotoNotAdded()
}