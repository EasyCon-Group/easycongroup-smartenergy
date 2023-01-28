package uz.easycongroup.smartenergy.presentation.presentation.common.controller.image

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderDetailImageBinding
import uz.easycongroup.smartenergy.presentation.utils.fresco.setimageUrlOrId
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener

internal class DetailImageItemController(
    private val onItemClicked: (String) -> Unit
) : BindableItemController<String, DetailImageItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: String) = "$ID_TAG${data}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<String>(parent, R.layout.view_holder_detail_image) {
        private lateinit var selectedItem: String

        private val binding = ViewHolderDetailImageBinding.bind(itemView)

        init {
            binding.root.setThrottledClickListener {
                onItemClicked.invoke(selectedItem)
            }
        }

        override fun bind(data: String) {
            selectedItem = data
            with(binding) {
                sdvAds.setimageUrlOrId(data)
            }
        }
    }

    private companion object {
        const val ID_TAG = "DetailImageItemController"
    }
}