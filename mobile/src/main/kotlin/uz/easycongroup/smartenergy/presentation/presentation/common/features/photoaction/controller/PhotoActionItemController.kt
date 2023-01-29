package uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderActionBinding
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.presentation.utils.iconResId
import uz.easycongroup.smartenergy.presentation.utils.nameResId
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener

internal class PhotoActionItemController(
    private val onItemClicked: (PhotoAction) -> Unit
) : BindableItemController<PhotoAction, PhotoActionItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: PhotoAction) = "$ID_TAG${data.name}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<PhotoAction>(parent, R.layout.view_holder_action) {
        private lateinit var selectedItem: PhotoAction

        private val binding = ViewHolderActionBinding.bind(itemView)

        init {
            binding.itemParent.setThrottledClickListener {
                onItemClicked.invoke(selectedItem)
            }
        }

        override fun bind(data: PhotoAction) {
            selectedItem = data
            with(binding) {
                tvName.setText(data.nameResId)
                ivIcon.setImageResource(data.iconResId)
            }
        }
    }


    private companion object {
        const val ID_TAG = "PhotoActionItemController"
    }
}