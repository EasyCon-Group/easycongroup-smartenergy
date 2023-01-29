package uz.easycongroup.smartenergy.domain.usecase.common.acttype

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.core.domain.corountines.MutableSharedFlowWrapper
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.data.repository.common.CommonTypesRepository
import uz.easycongroup.smartenergy.domain.sharedflow.global.acttype.ActTypeSharedFlow
import javax.inject.Inject

class ActTypeUseCaseImpl @Inject constructor(
    private val commonTypesRepository: CommonTypesRepository,
    private val actTypeSharedFlow: ActTypeSharedFlow
) : ActTypeUseCase {

    private var selectedItem: SelectionItem<ActType>? = null

    override val itemSelectionSharedFlow: MutableSharedFlowWrapper<SelectionItem<ActType>> =
        MutableSharedFlowWrapper()

    override fun setInitialData(value: ActType?) {
        if (value != null)
            selectedItem = SelectionItem(value, true)
    }

    override fun getAvailableItems(): Flow<List<SelectionItem<ActType>>> {
        return commonTypesRepository.getActTypes()
            .map { it -> it.map { SelectionItem(it, it == selectedItem?.data) } }
            .flowOn(Dispatchers.IO)
    }


    override fun setSelectedItem(value: SelectionItem<ActType>) {
        if (selectedItem?.data == value.data) return

        if (selectedItem != null)
            itemSelectionSharedFlow.tryEmit(selectedItem!!.copy(isSelected = false))

        selectedItem = value.copy(isSelected = true)
        itemSelectionSharedFlow.tryEmit(selectedItem!!)
    }

    override fun returnSelectedItem() {
        if (selectedItem != null)
            actTypeSharedFlow.tryEmit(selectedItem!!.data)
    }
}