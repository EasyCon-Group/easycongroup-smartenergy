package uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype

import moxy.MvpView
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType

interface SortTypeView : MvpView {

    fun onDefinedItems(items: List<SelectionItem<SortType>>)

    fun onDismissDialog()
}