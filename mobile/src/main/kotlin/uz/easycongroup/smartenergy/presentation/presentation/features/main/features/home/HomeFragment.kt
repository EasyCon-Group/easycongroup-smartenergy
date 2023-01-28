package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.FragmentHomeBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class HomeFragment : MvpAppCompatFragment(R.layout.fragment_home), HomeView {

    @Inject
    lateinit var lazyPresenter: Lazy<HomePresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity).globalDaggerComponent.inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }
    }

    override fun onDefinedViewVisibility(
        isManagerViewsVisible: Boolean,
        isSupervisorViewsVisible: Boolean,
        isCitizenViewsVisible: Boolean
    ) {
        with(binding) {
            llManager.isVisible = isManagerViewsVisible
            llSupervisor.isVisible = isSupervisorViewsVisible
            llCitizen.isVisible = isCitizenViewsVisible
        }
    }

    companion object {

        fun newInstance() = HomeFragment().withArguments()
    }
}