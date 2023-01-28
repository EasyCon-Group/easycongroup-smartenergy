package uz.easycongroup.smartenergy.presentation.presentation.features.payment.history.controller.decorator

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.Decorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.round.RoundMode
import uz.easycongroup.smartenergy.presentation.support.easyadapter.round.RoundOutlineProvider

internal class PaymentHistoryRoundViewHoldersGroupDrawer(
    private val viewTypeDay: Int,
    private val viewTypTransaction: Int,
    private val cornerRadius: Float
) : Decorator.ViewHolderDecor {

    override fun draw(
        canvas: Canvas,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {

        val viewHolder = recyclerView.getChildViewHolder(view)
        val nextViewHolder =
            recyclerView.findViewHolderForAdapterPosition(viewHolder.absoluteAdapterPosition + 1)
        val previousChildViewHolder =
            recyclerView.findViewHolderForAdapterPosition(viewHolder.absoluteAdapterPosition - 1)

        if (cornerRadius.compareTo(0f) != 0) {
            val roundMode = getRoundMode(previousChildViewHolder, viewHolder, nextViewHolder)
            val outlineProvider = view.outlineProvider
            if (outlineProvider is RoundOutlineProvider) {
                outlineProvider.roundMode = roundMode
                view.invalidateOutline()
            } else {
                view.outlineProvider = RoundOutlineProvider(cornerRadius, roundMode)
                view.clipToOutline = true
            }
        }
    }


    private fun getRoundMode(
        previousChildViewHolder: RecyclerView.ViewHolder?,
        currentViewHolder: RecyclerView.ViewHolder?,
        nextChildViewHolder: RecyclerView.ViewHolder?
    ): RoundMode {

        val previousHolderItemType = previousChildViewHolder?.itemViewType ?: -1
        val currentHolderItemType = currentViewHolder?.itemViewType ?: -1
        val nextHolderItemType = nextChildViewHolder?.itemViewType ?: -1

        val isCurrentInBottom =
            currentHolderItemType == viewTypTransaction && (nextHolderItemType == viewTypeDay || nextHolderItemType == -1)
        val isCurrentInTop = currentHolderItemType == viewTypeDay

        return when {
            isCurrentInBottom -> RoundMode.BOTTOM
            isCurrentInTop -> RoundMode.TOP
            else -> RoundMode.NONE
        }
    }
}