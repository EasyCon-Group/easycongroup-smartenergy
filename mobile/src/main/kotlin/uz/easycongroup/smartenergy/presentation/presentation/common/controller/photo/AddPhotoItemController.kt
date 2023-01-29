package uz.easycongroup.smartenergy.presentation.presentation.common.controller.photo

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderPhotoAddBinding
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.AddPhoto

internal class AddPhotoItemController(
    private val onItemClicked: (isPhotoAddingAvailable: Boolean) -> Unit
) : BindableItemController<AddPhoto, AddPhotoItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: AddPhoto) = "$ID_TAG${data.javaClass}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<AddPhoto>(parent, R.layout.view_holder_photo_add) {
        private lateinit var selectedItem: AddPhoto

        private val binding = ViewHolderPhotoAddBinding.bind(itemView)

        init {
            binding.root.setThrottledClickListener {
                onItemClicked.invoke(selectedItem.isPhotoAddingAvailable)
            }
        }

        override fun bind(data: AddPhoto) {
            selectedItem = data
        }
    }

    private companion object {
        const val ID_TAG = "AddPhotoItemController"
    }
}