package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderPaymentDayBinding
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem.HistoryDate

internal class PaymentDayItemController(
) : BindableItemController<HistoryDate, PaymentDayItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: HistoryDate) = "$ID_TAG${data.date}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<HistoryDate>(parent, R.layout.view_holder_payment_day) {

        private val binding = ViewHolderPaymentDayBinding.bind(itemView)

        override fun bind(data: HistoryDate) {
            with(binding) {
                tvPaymentDay.text = data.date
            }
        }
    }

    private companion object {
        const val ID_TAG = "PaymentDayItemController"
    }
}