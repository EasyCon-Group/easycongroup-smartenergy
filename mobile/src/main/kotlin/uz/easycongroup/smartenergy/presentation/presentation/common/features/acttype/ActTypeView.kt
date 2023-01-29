package uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype

import moxy.MvpView
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType

interface ActTypeView : MvpView {

    fun onDefinedItems(items: List<SelectionItem<ActType>>)

    fun onDismissDialog()
}