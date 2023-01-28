package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog

import moxy.MvpView
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent

internal interface CatalogView : MvpView {

    fun onDefinedCategoryListEvent(event: LoadingListEvent<Unit>)
}