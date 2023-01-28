package uz.easycongroup.smartenergy.presentation.presentation.common.features.progressdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import uz.easycongroup.smartenergy.R

class ProgressDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_progress_loading, container, false)
        isCancelable = false
        rootView.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
        return rootView
    }

    fun showProgressDialog(manager: FragmentManager) {
        super.show(manager, PROGRESS_DIALOG_TAG)
    }

    fun hideProgressDialog() {
        dismiss()
    }

    companion object {
        const val PROGRESS_DIALOG_TAG = "CustomProgressDialogFragment"
        fun newInstance(): ProgressDialogFragment =
            ProgressDialogFragment()
    }
}