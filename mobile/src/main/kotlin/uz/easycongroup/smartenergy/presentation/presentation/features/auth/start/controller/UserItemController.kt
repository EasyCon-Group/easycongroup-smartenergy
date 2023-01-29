package uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderUserBinding
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.presentation.utils.nameResId

internal class UserItemController(
    private val onItemClicked: (User) -> Unit
) : BindableItemController<User, UserItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: User) = "$ID_TAG${data.id}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<User>(parent, R.layout.view_holder_user) {
        private lateinit var selectedItem: User

        private val binding = ViewHolderUserBinding.bind(itemView)

        init {
            binding.root.setOnClickListener { onItemClicked.invoke(selectedItem) }
        }

        override fun bind(data: User) {
            selectedItem = data
            with(binding) {
                tvFullName.text = data.fullName
                tvInfo.text = data.info
                tvRole.setText(data.userRole.nameResId)
            }
        }
    }

    private companion object {
        const val ID_TAG = "ParentCategoryItemController"
    }
}