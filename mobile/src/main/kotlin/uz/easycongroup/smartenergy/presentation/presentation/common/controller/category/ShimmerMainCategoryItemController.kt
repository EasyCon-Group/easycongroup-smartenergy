package uz.easycongroup.smartenergy.presentation.presentation.common.controller.category

import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder
import ru.surfstudio.android.easyadapter.item.NoDataItem
import uz.easycongroup.smartenergy.R

class ShimmerMainCategoryItemController: NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup?) =
        BaseViewHolder(parent, R.layout.view_holder_main_category_shimmer).apply {
            itemView.findViewById<ShimmerFrameLayout>(R.id.shimmer).startShimmer()
        }

    override fun unbind(holder: BaseViewHolder?, item: NoDataItem<BaseViewHolder>?) {
        holder?.itemView?.findViewById<ShimmerFrameLayout>(R.id.shimmer)?.stopShimmer()
        super.unbind(holder, item)
    }
}