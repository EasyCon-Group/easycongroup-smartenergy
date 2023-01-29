package uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype.controller

import android.view.ViewGroup
import androidx.core.view.isVisible
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.databinding.ViewHolderActTypeBinding
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.presentation.utils.nameResId
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener

class ActTypeItemController(
    private val onItemClicked: (item: SelectionItem<ActType>) -> Unit
) : BindableItemController<SelectionItem<ActType>, ActTypeItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: SelectionItem<ActType>): Any = "$TAG_ID${data.data.name}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<SelectionItem<ActType>>(parent, R.layout.view_holder_act_type) {

        private val binding = ViewHolderActTypeBinding.bind(itemView)
        private lateinit var selectedItem: SelectionItem<ActType>

        init {
            binding.root.setThrottledClickListener { onItemClicked.invoke(selectedItem) }
        }

        override fun bind(data: SelectionItem<ActType>) {
            selectedItem = data
            with(binding) {
                ivSelection.isVisible = data.isSelected
                tvName.setText(data.data.nameResId)
            }
        }
    }

    companion object {
        const val TAG_ID = "UserTypeItemController"
    }
}