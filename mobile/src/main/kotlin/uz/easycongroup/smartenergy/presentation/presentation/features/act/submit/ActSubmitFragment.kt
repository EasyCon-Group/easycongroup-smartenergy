package uz.easycongroup.smartenergy.presentation.presentation.features.act.submit

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.FragmentActSubmitBinding
import uz.easycongroup.smartenergy.domain.data.models.act.type.ActType
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.photo.AddPhotoItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseMvpPhotoFragment
import uz.easycongroup.smartenergy.presentation.utils.nameResId
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import uz.easycongroup.smartenergy.presentation.utils.showErrorAlertDialog
import uz.easycongroup.smartenergy.presentation.utils.showToastInBottom
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.AddPhoto
import uz.easycongroup.smartenergy.domain.data.models.photo.PhotoListItem.PhotoUrl
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.photo.PhotoItemController
import javax.inject.Inject

internal class ActSubmitFragment : BaseMvpPhotoFragment(R.layout.fragment_act_submit),
    ActSubmitView {

    @Inject
    lateinit var lazyPresenter: Lazy<ActSubmitPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val binding by viewBinding(FragmentActSubmitBinding::bind)

    private val photosEasyAdapter = EasyAdapter()
    private val photoController = PhotoItemController(
        onItemClicked = {},
        onDeleteItemClicked = { presenter.deletePhotoFromPhotoList(it) }
    )
    private val addPhotoController = AddPhotoItemController { presenter.openPhotoActionSelection() }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

            rvPhotos.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            rvPhotos.itemAnimator = null
            rvPhotos.adapter = photosEasyAdapter

            /** common fields */
            ifvTitle.apply {
                addTextChangedListener {
                    presenter.setEnteredTitle(it)
                    if (errorText != null) errorText = null
                }
            }

            ifvActType.isEnabled = false
            ifvActType.actionListener = { presenter.openCategorySelectionScreen() }
            ifvDesc.apply {
                addTextChangedListener {
                    presenter.setEnteredDescription(it)
                    if (errorText != null) errorText = null
                }
            }

            btnPublish.setOnClickListener { presenter.publish() }
        }
    }

    override fun setImageUriFromCamera(uri: Uri) {
        presenter.setImageUriFromCamera(uri)
    }

    override fun setImageUriFromGallery(uri: Uri) {
        presenter.setImageUriFromGallery(uri)
    }

    override fun onCameraPermissionDeniedScreen(isRequiredOpenAppSettings: Boolean) {
        presenter.openPermissionDeniedScreen(isRequiredOpenAppSettings)
    }

    override fun onGalleryPermissionDeniedScreen(isRequiredOpenAppSettings: Boolean) {
        presenter.openPermissionDeniedScreen(isRequiredOpenAppSettings)
    }

    override fun onDefinedPhotoAddingNotAvailable() {
        if (presenter.isInRestoreState(this)) return

        showToastInBottom(getString(R.string.photo_limit_message_warning))
    }

    override fun onDefinedPhotoItems(items: List<PhotoListItem>) {
        val itemList = ItemList.create()
        items.forEach {
            when (it) {
                is AddPhoto -> itemList.add(it, addPhotoController)
                is PhotoUrl -> itemList.add(it, photoController)
            }
        }
        photosEasyAdapter.setItems(itemList)
    }

    override fun onOpenCamera() {
        if (presenter.isInRestoreState(this)) return

        checkPermissionAndTakePhoto()
    }

    override fun onOpenGallery() {
        if (presenter.isInRestoreState(this)) return

        checkPermissionAndChooseImage()
    }

    override fun onLoadingPortfolioCreation() {
        showProgressDialog()
    }

    override fun onSuccessPortfolioCreation() {
        hideProgressDialog()
    }

    override fun onFailurePortfolioCreation() {
        hideProgressDialog()

        if (!presenter.isInRestoreState(this))
            showErrorAlertDialog(message = getString(R.string.message_unknown_error))
    }

    override fun onFailurePortfolioCreationHideProgress() {
        hideProgressDialog()
    }

    override fun onDefinedActType(actType: ActType) {
        binding.ifvActType.distinctText = getString(actType.nameResId)
        binding.ifvActType.errorText = null
    }

    override fun onActTypeNotSelected() {
        binding.ifvActType.errorText = getString(R.string.act_submit_error_act_type_not_selected)
    }

    override fun onTitleNotValid() {
        binding.ifvTitle.errorText = getString(R.string.input_error_empty)
    }

    override fun onDescNotValid() {
        binding.ifvDesc.errorText = getString(R.string.input_error_empty)
    }

    override fun onPhotoNotAdded() {
        if (!presenter.isInRestoreState(this))
            showToastInBottom("Rasm qo'shish majburiy")
    }

    companion object {

        fun newInstance() =
            ActSubmitFragment().withArguments()
    }
}