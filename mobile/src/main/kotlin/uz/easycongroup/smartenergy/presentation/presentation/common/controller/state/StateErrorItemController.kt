package uz.easycongroup.smartenergy.presentation.presentation.common.controller.state

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder
import ru.surfstudio.android.easyadapter.item.NoDataItem
import uz.easycongroup.smartenergy.R

class StateErrorItemController(
    private val isFullScreen: Boolean = false,
    private val type: String = "",
    private val onActionClickLister: () -> Unit
) : NoDataItemController<BaseViewHolder>() {

    override fun getItemId(item: NoDataItem<BaseViewHolder>?): Any = "$ID_TAG$type"

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.view_holder_state_error).apply {
            itemView.findViewById<Button>(R.id.action_btn).setOnClickListener {
                onActionClickLister.invoke()
            }
            itemView.layoutParams = itemView.layoutParams.apply {
                height = if (isFullScreen) MATCH_PARENT else WRAP_CONTENT
            }
        }

    private companion object {
        const val ID_TAG = "CatalogProductItemController"
    }
}