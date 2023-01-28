package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller

import android.view.ViewGroup
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ViewHolderPaymentHistoryTransactionBinding
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem.HistoryTransaction
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryTransaction
import uz.easycongroup.smartenergy.presentation.utils.setThrottledClickListener
import uz.easycongroup.smartenergy.presentation.utils.text.TextUtils

internal class PaymentTransactionItemController(
    private val onItemClicked: (PaymentHistoryTransaction) -> Unit
) : BindableItemController<HistoryTransaction, PaymentTransactionItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: HistoryTransaction) = "$ID_TAG${data.transaction.id}"

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<HistoryTransaction>(
            parent,
            R.layout.view_holder_payment_history_transaction
        ) {
        private lateinit var selectedItem: HistoryTransaction

        private val binding = ViewHolderPaymentHistoryTransactionBinding.bind(itemView)

        init {
            binding.root.setThrottledClickListener { onItemClicked.invoke(selectedItem.transaction) }
        }

        override fun bind(data: HistoryTransaction) {
            selectedItem = data
            with(binding) {
                tvTransactionTime.text = data.transaction.time
                tvTransactionName.text = data.transaction.name
                val amount = TextUtils.formatMoney(data.transaction.amount)
                tvTransactionAmount.text = "$amount so’m"
            }
        }
    }

    private companion object {
        const val ID_TAG = "PaymentTransactionItemController"
    }
}