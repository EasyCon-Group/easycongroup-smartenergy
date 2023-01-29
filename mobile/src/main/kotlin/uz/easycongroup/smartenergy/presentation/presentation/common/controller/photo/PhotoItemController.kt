package uz.easycongroup.smartenergy.presentation.presentation.common.controller.photo

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderPhotoBinding
import uz.easycongroup.smartenergy.presentation.utils.fresco.setImageURI
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.PhotoUrl

internal class PhotoItemController(
    private val onItemClicked: (PhotoUrl) -> Unit,
    private val onDeleteItemClicked: (PhotoUrl) -> Unit
) : BindableItemController<PhotoUrl, PhotoItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: PhotoUrl) = "$ID_TAG${data.remoteUrl}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<PhotoUrl>(parent, R.layout.view_holder_photo) {
        private lateinit var selectedItem: PhotoUrl

        private val binding = ViewHolderPhotoBinding.bind(itemView)

        init {
            binding.root.setThrottledClickListener {
                onItemClicked.invoke(selectedItem)
            }
            binding.ivDelete.setOnClickListener {
                onDeleteItemClicked(selectedItem)
            }
        }

        override fun bind(data: PhotoUrl) {
            selectedItem = data
            with(binding) {
                sdvPhoto.setImageURI(data)
            }
        }
    }

    private companion object {
        const val ID_TAG = "PhotoItemController"
    }
}