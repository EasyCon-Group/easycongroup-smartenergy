package uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype.controller

import android.view.ViewGroup
import androidx.core.view.isVisible
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.databinding.ViewHolderSortTypeBinding
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.presentation.utils.nameResId
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener

class SortTypeItemController(
    private val onItemClicked: (item: SelectionItem<SortType>) -> Unit
) : BindableItemController<SelectionItem<SortType>, SortTypeItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: SelectionItem<SortType>): Any = "$TAG_ID${data.data.name}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<SelectionItem<SortType>>(parent, R.layout.view_holder_sort_type) {

        private val binding = ViewHolderSortTypeBinding.bind(itemView)
        private lateinit var selectedItem: SelectionItem<SortType>

        init {
            binding.root.setThrottledClickListener { onItemClicked.invoke(selectedItem) }
        }

        override fun bind(data: SelectionItem<SortType>) {
            selectedItem = data
            with(binding) {
                ivSelection.isVisible = data.isSelected
                tvName.setText(data.data.nameResId)
            }
        }
    }

    companion object {
        const val TAG_ID = "SortTypeItemController"
    }
}