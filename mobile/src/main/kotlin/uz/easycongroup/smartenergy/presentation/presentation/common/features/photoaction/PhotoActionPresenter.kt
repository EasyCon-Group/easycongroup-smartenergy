package uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.domain.usecase.common.photoaction.PhotoActionUseCase
import uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction.router.PhotoActionRouter
import javax.inject.Inject

class PhotoActionPresenter @Inject constructor(
    private val router: PhotoActionRouter,
    private val useCase: PhotoActionUseCase
) : MvpPresenter<PhotoActionView>() {

    fun setSelectedItem(value: PhotoAction) {
        useCase.setSelectedItem(value)
        returnSelectedItem()
    }

    override fun onFirstViewAttach() {
        getAvailableItems()
    }

    fun getAvailableItems() {
        useCase.getAvailableItems()
            .onStart { /*ignored*/ }
            .onEach { viewState.onDefinedItems(it) }
            .catch { /*ignored*/ }
            .launchIn(presenterScope)
    }

    fun returnSelectedItem() {
        useCase.returnSelectedItem()

        dismiss()
    }

    fun dismiss() {
        viewState.onDismissDialog()
    }
}