package uz.easycongroup.smartenergy.domain.usecase.common.sorttype

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.core.domain.corountines.MutableSharedFlowWrapper
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import uz.easycongroup.smartenergy.domain.sharedflow.global.sorttype.SortTypeSharedFlow
import javax.inject.Inject

class SortTypeUseCaseImpl @Inject constructor(
    private val commonTypesRepository: CommonTypesRepository,
    private val sortTypeSharedFlow: SortTypeSharedFlow
) : SortTypeUseCase {

    private var selectedItem: SelectionItem<SortType>? = null

    override val itemSelectionSharedFlow: MutableSharedFlowWrapper<SelectionItem<SortType>> =
        MutableSharedFlowWrapper()

    override fun setInitialData(value: SortType?) {
        if (value != null)
            selectedItem = SelectionItem(value, true)
    }

    override fun getAvailableItems(): Flow<List<SelectionItem<SortType>>> {
        return commonTypesRepository.getSortTypes()
            .map { it -> it.map { SelectionItem(it, it == selectedItem?.data) } }
            .flowOn(Dispatchers.IO)
    }

    override fun setSelectedItem(value: SelectionItem<SortType>) {
        if (selectedItem?.data == value.data) return

        if (selectedItem != null)
            itemSelectionSharedFlow.tryEmit(selectedItem!!.copy(isSelected = false))

        selectedItem = value.copy(isSelected = true)
        itemSelectionSharedFlow.tryEmit(selectedItem!!)
    }

    override fun resetSelection() {
        if (selectedItem != null)
            itemSelectionSharedFlow.tryEmit(selectedItem!!.copy(isSelected = false))

        selectedItem = null
    }

    override fun returnSelectedItem() {
        sortTypeSharedFlow.tryEmit(selectedItem?.data)
    }
}