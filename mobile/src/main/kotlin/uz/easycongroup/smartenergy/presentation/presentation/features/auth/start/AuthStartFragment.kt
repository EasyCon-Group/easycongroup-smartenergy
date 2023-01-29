package uz.easycongroup.smartenergy.presentation.presentation.features.auth.start

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.presentation.event.LoadingContentEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingContentEvent.*
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.databinding.FragmentAuthStartBinding
import uz.easycongroup.smartenergy.domain.data.models.user.User
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateEmptyItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateErrorItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.controller.ShimmerUserItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.auth.start.controller.UserItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseMvpFragment
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class AuthStartFragment : BaseMvpFragment(R.layout.fragment_auth_start),
    AuthStartView {

    @Inject
    lateinit var lazyPresenter: Lazy<AuthStartPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val binding: FragmentAuthStartBinding by viewBinding()

    private val easyAdapter = EasyAdapter()
    private val errorItemController = StateErrorItemController(true) {
        presenter.getUserList()
    }
    private val emptyItemController = StateEmptyItemController(
        isFullScreen = true,
        drawableResId = R.drawable.ic_state_empty,
        messageResId = R.string.state_no_items
    )
    private val userItemController = UserItemController { presenter.saveSelectedUser(it) }
    private val shimmerUserItemController = ShimmerUserItemController()


    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            rvUsers.layoutManager = LinearLayoutManager(requireContext())
            rvUsers.itemAnimator = null
            rvUsers.adapter = easyAdapter
        }
    }

    override fun onDefinedUserListEvent(event: LoadingListEvent<User>) {
        val itemList = ItemList.create()
        when (event) {
            is SuccessList -> itemList.addAll(event.data, userItemController)
            is LoadingList -> {
                itemList.add(shimmerUserItemController)
                itemList.add(shimmerUserItemController)
                itemList.add(shimmerUserItemController)
                itemList.add(shimmerUserItemController)
                itemList.add(shimmerUserItemController)
            }
            is EmptyList -> itemList.add(emptyItemController)
            is ErrorList -> itemList.add(errorItemController)
        }
        easyAdapter.setItems(itemList)
    }

    override fun onDefinedUserSavingEvent(event: LoadingContentEvent<Unit>) {
        when(event){
            is LoadingContent -> showLoadingDialog()
            is SuccessContent -> hideLoadingDialog()
            is ErrorContent -> hideLoadingDialog()
        }
    }

    companion object {

        fun newInstance() =
            AuthStartFragment().withArguments()
    }
}