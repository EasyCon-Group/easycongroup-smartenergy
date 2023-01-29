package uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.domain.usecase.common.acttype.ActTypeUseCase
import uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype.router.ActTypeRouter
import javax.inject.Inject

class ActTypePresenter @Inject constructor(
    private val router: ActTypeRouter,
    private val useCase: ActTypeUseCase
) : MvpPresenter<ActTypeView>() {

    private val items: MutableList<SelectionItem<ActType>> = mutableListOf()

    fun setInitialData(actType: ActType?) =
        useCase.setInitialData(actType)

    fun setSelectedItem(value: SelectionItem<ActType>) =
        useCase.setSelectedItem(value)

    override fun onFirstViewAttach() {
        getAvailableItems()

        subscribeItemSelection()
    }

    fun getAvailableItems() {
        useCase.getAvailableItems()
            .onStart { /*ignored*/ }
            .onEach {
                items.clear()
                items.addAll(it)

                viewState.onDefinedItems(items)
            }
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

    private fun subscribeItemSelection() {
        presenterScope.launch {
            useCase.itemSelectionSharedFlow
                .collect { item ->
                    val index = items.indexOfFirst { it.data == item.data }
                    if (index >= 0) items[index] = item

                    viewState.onDefinedItems(items)
                }
        }
    }
}
