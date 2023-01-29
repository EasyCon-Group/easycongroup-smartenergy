package uz.easycongroup.smartenergy.domain.usecase.common.acttype

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.core.domain.corountines.MutableSharedFlowWrapper
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType

interface ActTypeUseCase {

    val itemSelectionSharedFlow: MutableSharedFlowWrapper<SelectionItem<ActType>>

    fun setInitialData(value: ActType?)

    fun getAvailableItems(): Flow<List<SelectionItem<ActType>>>

    fun setSelectedItem(value: SelectionItem<ActType>)

    fun returnSelectedItem()
}