package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import moxy.MvpPresenter
import moxy.presenterScope
import timber.log.Timber
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.presentation.application.manager.resource.ResourceManager
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.router.CatalogRouter
import javax.inject.Inject

internal class CatalogPresenter @Inject constructor(
    private val resourceManager: ResourceManager,
    private val router: CatalogRouter
) : MvpPresenter<CatalogView>() {


    override fun onFirstViewAttach() {
        getCatalogCategoryList()
    }

    fun getCatalogCategoryList() {
    }

    fun back() =
        router.back()
}