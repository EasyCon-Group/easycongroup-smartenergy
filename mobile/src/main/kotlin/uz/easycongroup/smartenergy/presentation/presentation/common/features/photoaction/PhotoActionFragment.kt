package uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.databinding.FragmentPhotoActionBinding
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoAction
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.features.photoaction.controller.PhotoActionItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseBottomSheetDialogFragment
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class PhotoActionFragment : BaseBottomSheetDialogFragment(), PhotoActionView {

    @Inject
    @InjectPresenter
    lateinit var presenter: PhotoActionPresenter

    @ProvidePresenter
    fun presenter(): PhotoActionPresenter = presenter

    private lateinit var binding: FragmentPhotoActionBinding

    private val easyAdapter = EasyAdapter()
    private val photoActionController = PhotoActionItemController {
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
    ): View = FragmentPhotoActionBinding.inflate(inflater).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivClose.setOnClickListener { presenter.dismiss() }
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = easyAdapter
        }
    }

    override fun onDefinedItems(items: List<PhotoAction>) {
        val itemList = ItemList.create()
        itemList.addAll(items, photoActionController)
        easyAdapter.setItems(itemList)
    }

    override fun onDismissDialog() {
        dismiss()
    }

    companion object {

        fun newInstance() =
            PhotoActionFragment().withArguments()
    }
}