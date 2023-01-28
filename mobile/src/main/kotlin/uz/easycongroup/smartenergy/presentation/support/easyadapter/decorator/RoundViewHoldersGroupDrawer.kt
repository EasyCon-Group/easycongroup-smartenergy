package uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator

import android.graphics.Canvas
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.Decorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.round.RoundMode
import uz.easycongroup.smartenergy.presentation.support.easyadapter.round.RoundOutlineProvider

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal class RoundViewHoldersGroupDrawer(
    private val viewTypes: List<Int>,
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
            recyclerView.findViewHolderForAdapterPosition(viewHolder.adapterPosition + 1)
        val previousChildViewHolder =
            recyclerView.findViewHolderForAdapterPosition(viewHolder.adapterPosition - 1)

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

        val isPreviousInGroup = viewTypes.contains(previousHolderItemType)
        val isCurrentInGroup = viewTypes.contains(currentHolderItemType)
        val isNextInGroup = viewTypes.contains(nextHolderItemType)

        return when {
            isCurrentInGroup -> when {
                isPreviousInGroup && !isNextInGroup -> RoundMode.BOTTOM
                !isPreviousInGroup && isNextInGroup -> RoundMode.TOP
                else -> RoundMode.NONE
            }
            else -> RoundMode.NONE
        }
    }
}