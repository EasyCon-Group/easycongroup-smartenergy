package uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype

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
import uz.easycongroup.smartenergy.databinding.FragmentActTypeBinding
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.getNullableBundleArgument
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseBottomSheetDialogFragment
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import uz.easycongroup.smartenergy.presentation.presentation.common.features.acttype.controller.ActTypeItemController
import javax.inject.Inject

internal class ActTypeFragment : BaseBottomSheetDialogFragment(), ActTypeView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ActTypePresenter

    @ProvidePresenter
    fun presenter(): ActTypePresenter = presenter.apply {
        setInitialData(getNullableBundleArgument(KEY_ACT_TYPE))
    }

    private lateinit var binding: FragmentActTypeBinding

    private val easyAdapter = EasyAdapter()
    private val actTypeItemController = ActTypeItemController {
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
    ): View = FragmentActTypeBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivClose.setOnClickListener { presenter.dismiss() }
            btnSave.setOnClickListener { presenter.returnSelectedItem() }
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = easyAdapter
        }
    }

    override fun onDefinedItems(items: List<SelectionItem<ActType>>) {
        val itemList = ItemList.create()
        itemList.addAll(items, actTypeItemController)
        easyAdapter.setItems(itemList)
    }

    override fun onDismissDialog() {
        dismiss()
    }

    companion object {

        private const val KEY_ACT_TYPE = "act_type"

        fun newInstance(actType: ActType?) =
            ActTypeFragment().withArguments {
                putSerializable(KEY_ACT_TYPE, actType)
            }
    }
}