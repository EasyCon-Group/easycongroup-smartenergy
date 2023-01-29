package uz.easycongroup.smartenergy.presentation.support.moxy

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import moxy.MvpBottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment : MvpBottomSheetDialogFragment() {

    open fun isCanFitToContents(): Boolean = true
    open fun isRequiredSetForcedFullScreen(): Boolean = false
    open fun getInitialState(): Int = BottomSheetBehavior.STATE_EXPANDED

    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                (this@BaseBottomSheetDialogFragment.dialog as BottomSheetDialog)
                    .behavior.apply {
                        isFitToContents = isCanFitToContents()
                        setState(getInitialState())
                    }
            }
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()

        if (isRequiredSetForcedFullScreen()) {
            dialog?.also {
                val bottomSheet = it.findViewById<View>(R.id.design_bottom_sheet)
                bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.peekHeight = resources.displayMetrics.heightPixels
                view?.requestLayout()
            }
        }
    }

}