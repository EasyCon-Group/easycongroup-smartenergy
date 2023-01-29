package uz.easycongroup.smartenergy.presentation.support.moxy

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment
import uz.easycongroup.smartenergy.presentation.presentation.common.features.progressdialog.ProgressDialogFragment

internal abstract class BaseMvpFragment(
    @LayoutRes contentLayoutId: Int
) : MvpAppCompatFragment(contentLayoutId) {

    protected var progressDialog: ProgressDialogFragment? = null

    protected fun showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialogFragment.newInstance()
        }
        progressDialog?.showProgressDialog(childFragmentManager)
    }

    protected fun hideLoadingDialog() {
        progressDialog?.hideProgressDialog()
    }
}