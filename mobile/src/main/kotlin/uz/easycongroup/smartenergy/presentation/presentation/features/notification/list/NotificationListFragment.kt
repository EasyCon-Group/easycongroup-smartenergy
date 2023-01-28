package uz.easycongroup.smartenergy.presentation.presentation.features.notification.list

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
import uz.easycongroup.smartenergy.databinding.FragmentNotificationListBinding
import uz.easycongroup.smartenergy.domain.data.models.notification.Notification
import uz.easycongroup.smartenergy.domain.data.models.notification.UnreadInfo
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateEmptyItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateErrorItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateLoadingItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.notification.list.controller.NotificationItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.easyadapter.linearDividerDecorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.Rules
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import uz.easycongroup.smartenergy.presentation.utils.toPx
import javax.inject.Inject

internal class NotificationListFragment : MvpAppCompatFragment(R.layout.fragment_notification_list),
    NotificationListView {

    @Inject
    lateinit var lazyPresenter: Lazy<NotificationListPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val easyAdapter = EasyAdapter()
    private val loadingItemController = StateLoadingItemController(true)
    private val errorItemController = StateErrorItemController(true) {
        presenter.getNotificationList()
    }
    private val emptyItemController = StateEmptyItemController(
        isFullScreen = true,
        drawableResId = R.drawable.ic_notification,
        messageResId = R.string.notification_list_empty_message
    )
    private val notificationItemController = NotificationItemController { }

    private val binding: FragmentNotificationListBinding by viewBinding()


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

            rvNotifications.layoutManager = LinearLayoutManager(requireContext())
            rvNotifications.itemAnimator = null
            rvNotifications.adapter = easyAdapter

            val dividerDrawerCategoryMiddle = linearDividerDecorator(
                color = ContextCompat.getColor(requireContext(), R.color.color_divider_color),
                height = 1.toPx,
                rule = Rules.MIDDLE,
                paddingStart = 90.toPx
            )
            rvNotifications.addItemDecoration(
                Decorator.Builder()
                    .overlay(notificationItemController.viewType() to dividerDrawerCategoryMiddle)
                    .build()
            )
        }
    }

    override fun onDefinedNotificationsEvent(event: LoadingListEvent<Notification>) {
        val itemList = ItemList.create()
        when (event) {
            is SuccessList -> itemList.addAll(event.data, notificationItemController)
            is LoadingList -> itemList.add(loadingItemController)
            is EmptyList -> itemList.add(emptyItemController)
            is ErrorList -> itemList.add(errorItemController)
        }
        easyAdapter.setItems(itemList)
    }

    override fun onDefinedUnreadInfo(unreadInfo: UnreadInfo) {

    }

    companion object {

        fun newInstance() =
            NotificationListFragment().withArguments()
    }
}
