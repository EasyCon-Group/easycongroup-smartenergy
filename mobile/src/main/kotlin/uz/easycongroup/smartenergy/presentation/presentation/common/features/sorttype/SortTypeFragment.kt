package uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.core.data.models.selectable.SelectionItem
import uz.easycongroup.smartenergy.databinding.FragmentSortTypeBinding
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.features.sorttype.controller.SortTypeItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.getNullableBundleArgument
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseBottomSheetDialogFragment
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class SortTypeFragment : BaseBottomSheetDialogFragment(), SortTypeView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SortTypePresenter

    @ProvidePresenter
    fun presenter(): SortTypePresenter = presenter.apply {
        setInitialData(getNullableBundleArgument(KEY_SORT_TYPE))
    }

    private lateinit var binding: FragmentSortTypeBinding

    private val easyAdapter = EasyAdapter()
    private val sortTypeItemController = SortTypeItemController {
        presenter.setSelectedItem(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { dismiss() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSortTypeBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivClose.setOnClickListener { presenter.dismiss() }
            btnReset.setOnClickListener { presenter.resetSelection() }
            btnSave.setOnClickListener { presenter.returnSelectedItem() }

            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = easyAdapter
        }
    }

    override fun onDefinedItems(items: List<SelectionItem<SortType>>) {
        val itemList = ItemList.create()
        itemList.addAll(items, sortTypeItemController)
        easyAdapter.setItems(itemList)
    }

    override fun onDismissDialog() {
        dismiss()
    }

    companion object {

        private const val KEY_SORT_TYPE = "sort_type"

        fun newInstance(sortType: SortType?) =
            SortTypeFragment().withArguments {
                putSerializable(KEY_SORT_TYPE, sortType)
            }
    }
}