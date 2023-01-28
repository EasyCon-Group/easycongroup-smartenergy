package uz.easycongroup.smartenergy.presentation.presentation.common.controller.state

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder
import uz.easycongroup.smartenergy.R

class StateEmptyItemController(
    private val isFullScreen: Boolean = false,
    @DrawableRes val drawableResId: Int = R.drawable.ic_state_info,
    val messageResId: Int = R.string.state_no_items
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup?) =
        BaseViewHolder(parent, R.layout.view_holder_state_empty).apply {
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
            itemView.findViewById<ImageView>(R.id.state_icon_iv).setImageResource(drawableResId)
            itemView.findViewById<TextView>(R.id.tv_message).setText(messageResId)
        }
}