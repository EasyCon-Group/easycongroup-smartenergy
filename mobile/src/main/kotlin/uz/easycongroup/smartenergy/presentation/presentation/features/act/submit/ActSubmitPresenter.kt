package uz.easycongroup.smartenergy.presentation.presentation.features.act.submit

import android.net.Uri
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction.ACTION_ADD_FROM_CAMERA
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction.ACTION_ADD_FROM_GALLERY
import uz.easycongroup.smartenergy.domain.sharedflow.global.acttype.ActTypeSharedFlow
import uz.easycongroup.smartenergy.domain.sharedflow.global.photoaction.PhotoActionSharedFlow
import uz.easycongroup.smartenergy.domain.usecase.act.ActSubmitUseCase
import uz.easycongroup.smartenergy.presentation.presentation.features.act.submit.router.ActSubmitRouter
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.PhotoUrl
import uz.easycongroup.smartenergy.domain.usecase.act.exception.ActSubmitException
import javax.inject.Inject

class ActSubmitPresenter @Inject constructor(
    private val actTypeSharedFlow: ActTypeSharedFlow,
    private val photoActionSharedFlow: PhotoActionSharedFlow,
    private val router: ActSubmitRouter,
    private val useCase: ActSubmitUseCase
) : MvpPresenter<ActSubmitView>() {

    override fun onFirstViewAttach() {
        viewState.onDefinedPhotoItems(useCase.getPhotoListItems())

        subscribeSharedFlows()
    }


    fun publish() {
        useCase.submitAct()
            .onStart { viewState.onLoadingPortfolioCreation() }
            .onEach {
                viewState.onSuccessPortfolioCreation()
                back()
            }
            .catch {
                Timber.e(it)
                when (it) {
                    is ActSubmitException -> {
                        viewState.onFailurePortfolioCreationHideProgress()
                        when {
                            it.isTitleEmpty -> viewState.onTitleNotValid()
                            it.isActTypeNotSelected -> viewState.onActTypeNotSelected()
                            it.isDescriptionEmpty -> viewState.onDescNotValid()
                            it.isPhoneNotAdded -> viewState.onPhotoNotAdded()
                        }
                    }
                    else -> viewState.onFailurePortfolioCreation()
                }
            }
            .launchIn(presenterScope)
    }

    fun setImageUriFromCamera(uri: Uri) {
        useCase.setImageUriFromCamera(uri)
        viewState.onDefinedPhotoItems(useCase.getPhotoListItems())
    }

    fun setImageUriFromGallery(uri: Uri) {
        useCase.setImageUriFromGallery(uri)
        viewState.onDefinedPhotoItems(useCase.getPhotoListItems())
    }

    fun deletePhotoFromPhotoList(photoUrl: PhotoUrl) {
        useCase.deletePhotoFromPhotoList(photoUrl)
        viewState.onDefinedPhotoItems(useCase.getPhotoListItems())
    }

    fun openPermissionDeniedScreen(isRequiredOpenAppSettings: Boolean) {
//        if (isRequiredOpenAppSettings)
//            router.openPermissionDeniedScreen()
    }

    fun setEnteredTitle(value: String) =
        useCase.setEnteredTitle(value)

    fun setEnteredDescription(value: String) =
        useCase.setEnteredDescription(value)

    fun openCategorySelectionScreen() =
        router.openActTypeSelectionScreen(useCase.getSelectedActType())

    fun openPhotoActionSelection() {
        if (useCase.isPhotoAddingAvailable) router.openPhotoActionSelectionScreen()
        else viewState.onDefinedPhotoAddingNotAvailable()
    }

    fun back() =
        router.back()

    private fun subscribeSharedFlows() {
        presenterScope.launch {
            actTypeSharedFlow
                .onEach {
                    viewState.onDefinedActType(it)
                    useCase.setSelectedActType(it)
                }
                .collect()
        }

        presenterScope.launch {
            photoActionSharedFlow
                .onEach {
                    when (it) {
                        ACTION_ADD_FROM_CAMERA -> viewState.onOpenCamera()
                        ACTION_ADD_FROM_GALLERY -> viewState.onOpenGallery()
                    }
                }
                .collect()
        }
    }
}