package uz.easycongroup.smartenergy.presentation.presentation.common.controller.state

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import uz.easycongroup.smartenergy.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class StateLoadingItemController(
    private val isFullScreen: Boolean = false
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.view_holder_state_loading).apply {
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
        }
}