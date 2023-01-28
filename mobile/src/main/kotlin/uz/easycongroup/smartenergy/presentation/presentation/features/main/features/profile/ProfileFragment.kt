package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.profile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.FragmentProfileBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class ProfileFragment : MvpAppCompatFragment(R.layout.fragment_profile), ProfileView {

    @Inject
    lateinit var lazyPresenter: Lazy<ProfilePresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }


    private val binding: FragmentProfileBinding by viewBinding()

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
            tvPersonalData.setOnClickListener { }
            tvPaymentHistory.setOnClickListener { presenter.openPaymentHistoryListScreen() }
            tvNotifications.setOnClickListener { presenter.openNotificationListScreen() }
            tvCards.setOnClickListener { }
            tvSavedDevices.setOnClickListener { }
            tvLogout.setOnClickListener { presenter.logout() }
        }
    }

    companion object {

        fun newInstance() =
            ProfileFragment().withArguments()
    }
}