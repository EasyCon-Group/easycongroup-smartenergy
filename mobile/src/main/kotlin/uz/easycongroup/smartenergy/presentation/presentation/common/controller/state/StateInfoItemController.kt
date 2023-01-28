package uz.easycongroup.smartenergy.presentation.presentation.common.controller.state

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import uz.easycongroup.smartenergy.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class StateInfoItemController(
    private val isFullScreen: Boolean = false,
    private val messageResId: Int? = null,
    private val iconResId: Int? = null
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.view_holder_state_info).apply {
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
            if (messageResId != null) {
                itemView.findViewById<TextView>(R.id.info_tv).setText(messageResId)
            }
            if (iconResId != null && iconResId > 0) {
                itemView.findViewById<ImageView>(R.id.info_iv).setImageResource(iconResId)
            }
        }
}