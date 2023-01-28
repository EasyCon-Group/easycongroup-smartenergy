package uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.domain.usecase.common.sorttype.SortTypeUseCase
import uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype.router.SortTypeRouter
import javax.inject.Inject

class SortTypePresenter @Inject constructor(
    private val router: SortTypeRouter,
    private val useCase: SortTypeUseCase
) : MvpPresenter<SortTypeView>() {

    private val items: MutableList<SelectionItem<SortType>> = mutableListOf()

    fun setInitialData(value: SortType?) =
        useCase.setInitialData(value)

    fun setSelectedItem(value: SelectionItem<SortType>) =
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

    fun resetSelection() {
        useCase.resetSelection()
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
