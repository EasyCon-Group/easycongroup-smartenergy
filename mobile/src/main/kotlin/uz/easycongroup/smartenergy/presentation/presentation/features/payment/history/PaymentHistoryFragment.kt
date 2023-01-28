package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.recycler.decorator.Decorator
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.databinding.FragmentPaymentHistoryBinding
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem
import uz.easycongroup.smartenergy.domain.data.models.payment.history.PaymentHistoryListItem.*
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateEmptyItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateErrorItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateLoadingItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller.PaymentDayItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller.PaymentTransactionItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller.decorator.PaymentHistoryRoundViewHoldersGroupDrawer
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator.SimpleOffsetDecorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.linearDividerDecorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.Rules
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import uz.easycongroup.smartenergy.presentation.utils.toPx
import javax.inject.Inject

internal class PaymentHistoryFragment : MvpAppCompatFragment(R.layout.fragment_payment_history),
    PaymentHistoryView {

    @Inject
    lateinit var lazyPresenter: Lazy<PaymentHistoryPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val easyAdapter = EasyAdapter()
    private val loadingItemController = StateLoadingItemController(true)
    private val errorItemController = StateErrorItemController(true) {
        presenter.getPaymentHistories()
    }
    private val emptyItemController = StateEmptyItemController(
        isFullScreen = true,
        drawableResId = R.drawable.ic_profile_payment_history,
        messageResId = R.string.payment_history_empty
    )
    private val dayController = PaymentDayItemController()
    private val transactionController = PaymentTransactionItemController {}

    private val binding by viewBinding(FragmentPaymentHistoryBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            rvPaymentHistory.layoutManager = LinearLayoutManager(requireContext())
            rvPaymentHistory.adapter = easyAdapter
            rvPaymentHistory.itemAnimator = null

            val roundViewHoldersGroupDrawer =
                PaymentHistoryRoundViewHoldersGroupDrawer(
                    viewTypeDay = dayController.viewType(),
                    viewTypTransaction = transactionController.viewType(),
                    12.toPx.toFloat()
                )


            val simpleOffsetTopDecorator = SimpleOffsetDecorator(0.toPx, 16.toPx, 0.toPx, 0.toPx)
            val simpleOffsetBottomDecorator = SimpleOffsetDecorator(0.toPx, 0.toPx, 0.toPx, 0.toPx)

            val decorator = Decorator.Builder()

                .underlay(dayController.viewType() to roundViewHoldersGroupDrawer)
                .underlay(transactionController.viewType() to roundViewHoldersGroupDrawer)
                .offset(dayController.viewType() to simpleOffsetTopDecorator)
                .offset(transactionController.viewType() to simpleOffsetBottomDecorator)
                .build()

            rvPaymentHistory.addItemDecoration(decorator)
        }
    }

    override fun onDefinedPaymentHistoryEvent(event: LoadingListEvent<PaymentHistoryListItem>) {
        val itemList = ItemList.create()
        when (event) {
            is LoadingList -> itemList.add(loadingItemController)
            is SuccessList -> {
                event.data.forEach {
                    when (it) {
                        is HistoryDate -> itemList.add(it, dayController)
                        is HistoryTransaction -> itemList.add(it, transactionController)
                    }
                }
            }
            is EmptyList -> itemList.add(emptyItemController)
            is ErrorList -> itemList.add(errorItemController)
        }
        easyAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() =
            PaymentHistoryFragment().withArguments()
    }
}