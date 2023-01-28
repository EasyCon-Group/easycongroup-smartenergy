package uz.easycongroup.smartenergy.domain.usecase.common.sorttype

import kotlinx.coroutines.flow.Flow
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.core.domain.corountines.MutableSharedFlowWrapper
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType

interface SortTypeUseCase {

    val itemSelectionSharedFlow: MutableSharedFlowWrapper<SelectionItem<SortType>>

    fun setInitialData(value: SortType?)

    fun getAvailableItems(): Flow<List<SelectionItem<SortType>>>

    fun setSelectedItem(value: SelectionItem<SortType>)

    fun resetSelection()

    fun returnSelectedItem()
}