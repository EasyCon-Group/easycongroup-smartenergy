package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.databinding.FragmentCatalogBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateEmptyItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateErrorItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.controller.ShimmerParentCategoryItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog),
    CatalogView {

    @Inject
    lateinit var lazyPresenter: Lazy<CatalogPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val easyAdapter = EasyAdapter()
    private val errorItemController = StateErrorItemController(true) {
        presenter.getCatalogCategoryList()
    }
    private val emptyItemController = StateEmptyItemController(
        isFullScreen = true,
        drawableResId = R.drawable.ic_state_empty,
        messageResId = R.string.state_no_items
    )
    private val shimmerCategoryItemController = ShimmerParentCategoryItemController()

    private val binding: FragmentCatalogBinding by viewBinding()

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
            rvCategories.layoutManager = LinearLayoutManager(requireContext())
            rvCategories.itemAnimator = null
            rvCategories.adapter = easyAdapter
        }
    }

    override fun onDefinedCategoryListEvent(event: LoadingListEvent<Unit>) {
        val itemList = ItemList.create()
        when (event) {
            is SuccessList -> {}
            is LoadingList -> {
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
            }
            is EmptyList -> itemList.add(emptyItemController)
            is ErrorList -> itemList.add(errorItemController)
        }
        easyAdapter.setItems(itemList)
    }

    companion object {

        fun newInstance() =
            CatalogFragment().withArguments()
    }
}
