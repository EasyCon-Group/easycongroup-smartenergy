package uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderNotificationBinding
import uz.easycongroup.smartenergy.domain.data.models.notification.Notification
import uz.easycongroup.smartenergy.presentation.utils.fresco.setImageUrlOrId

internal class NotificationItemController(
    private val onClicked: (notification: Notification) -> Unit,
) : BindableItemController<Notification, NotificationItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: Notification) = "${ID_TAG}${data.id}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<Notification>(parent, R.layout.view_holder_notification) {

        private lateinit var selectedItem: Notification
        private val binding = ViewHolderNotificationBinding.bind(itemView)

        init {
            with(binding) {
                root.setOnClickListener { onClicked.invoke(selectedItem) }
            }
        }

        override fun bind(data: Notification) {
            selectedItem = data
            with(binding) {
                sdvNotificationState.setImageUrlOrId(data.image)
                tvNotificationMessage.text = data.message
                tvNotificationDate.text = data.date
            }
        }
    }

    private companion object {
        const val ID_TAG = "NotificationItemController"
    }
}