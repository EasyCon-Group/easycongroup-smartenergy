package uz.easycongroup.smartenergy.core.presentation.customview.errortext

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible

class ErrorTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = android.R.attr.textStyle
) : AppCompatTextView(context, attrs, defStyle) {

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        isVisible = !text.isNullOrEmpty()
    }

}