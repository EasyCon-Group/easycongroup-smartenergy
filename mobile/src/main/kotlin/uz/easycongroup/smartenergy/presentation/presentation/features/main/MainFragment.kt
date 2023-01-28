package uz.easycongroup.smartenergy.presentation.presentation.features.main

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.FragmentMainBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter.MainTabs
import uz.easycongroup.smartenergy.presentation.presentation.features.main.router.MainRouter.MainTabs.*
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.getNonNullBundleArgument
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import javax.inject.Inject

internal class MainFragment : MvpAppCompatFragment(R.layout.fragment_main), MainView {

    @Inject
    lateinit var lazyPresenter: Lazy<MainPresenter>
    private val presenter by moxyPresenter {
        lazyPresenter.get().apply {
            setInitialStartTab(getNonNullBundleArgument(START_TAB))
        }
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var supportAppNavigator: SupportAppNavigator? = null

    private val binding: FragmentMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .mainDaggerComponent
            .create()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        supportAppNavigator =
            SupportAppNavigator(requireActivity(), childFragmentManager, binding.frameLayout.id)

        with(binding) {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.main_home -> presenter.openHomeTab().let { true }
                    R.id.main_catalog -> presenter.openCatalogTab().let { true }
                    R.id.main_profile -> presenter.openProfileTab().let { true }
                    else -> false
                }
            }
            bottomNavigationView.setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.main_home -> presenter.openHomeTab()
                    R.id.main_catalog -> presenter.openCatalogTab()
                    R.id.main_profile -> presenter.openProfileTab()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.removeNavigator()
        supportAppNavigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        supportAppNavigator = null
        super.onDestroyView()
    }


    override fun onDefinedUnreadNotificationCount(count: Int) {
        if (count > 0)
            binding.bottomNavigationView.getOrCreateBadge(R.id.main_profile)
                .apply { this.number = count }
        else
            binding.bottomNavigationView.removeBadge(R.id.main_profile)
    }

    override fun onTabChanged(tab: MainTabs) {
        val selectedMenuItemId: Int = when (tab) {
            HomeTab -> R.id.main_home
            CatalogTab -> R.id.main_catalog
            ProfileTab -> R.id.main_profile
        }

        if (selectedMenuItemId > 0) {
            binding.bottomNavigationView.setOnItemSelectedListener(null)
            binding.bottomNavigationView.setOnItemReselectedListener(null)
            binding.bottomNavigationView.selectedItemId = selectedMenuItemId
        }
    }

    companion object {

        private const val START_TAB = "start_tab"

        fun newInstance(tab: MainTabs) =
            MainFragment().withArguments {
                putSerializable(START_TAB, tab)
            }
    }
}