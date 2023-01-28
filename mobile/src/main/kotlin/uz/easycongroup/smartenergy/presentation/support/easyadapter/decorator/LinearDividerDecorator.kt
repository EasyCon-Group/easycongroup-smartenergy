package uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.Decorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.gap.Gap
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.Rules

class LinearDividerDecorator(
    gap: Gap
) : Decorator.ViewHolderDecor {

    private val dividerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = gap.color
        strokeWidth = gap.height.toFloat()
    }
    private val alpha = dividerPaint.alpha
    private val paddingStart: Int = gap.paddingStart
    private val paddingEnd: Int = gap.paddingEnd
    private val dividerDrawingRule = gap.rule

    override fun draw(
        canvas: Canvas,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val currentViewHolder = recyclerView.getChildViewHolder(view)
        val nextViewHolder =
            recyclerView.findViewHolderForAdapterPosition(currentViewHolder.adapterPosition + 1)
        val prevViewHolder =
            recyclerView.findViewHolderForAdapterPosition(currentViewHolder.adapterPosition - 1)

        val startX = recyclerView.paddingLeft + paddingStart
        val startY = if (dividerDrawingRule == Rules.TOP) {
            view.top + view.translationY
        } else {
            view.bottom + view.translationY
        }
        val stopX = recyclerView.width - recyclerView.paddingRight - paddingEnd
        val stopY = startY

        dividerPaint.alpha = (view.alpha * alpha).toInt()

        val isLastHolder = nextViewHolder == null
        val isFirstHolder = prevViewHolder == null
        val areSameWithPrev = prevViewHolder?.itemViewType == currentViewHolder?.itemViewType
        val areSameHolders =
            currentViewHolder.itemViewType == nextViewHolder?.itemViewType ?: UNDEFINE_VIEW_HOLDER

        val shouldDrawMiddle = shouldDrawMiddleDivider(areSameHolders)
        val shouldDrawBottom = shouldDrawBottomDivider(areSameHolders, isLastHolder)
        val shouldDrawTop = shouldDrawTopDivider(areSameWithPrev, isFirstHolder)

        val shouldDrawDivider =
            shouldDrawDivider(shouldDrawMiddle, shouldDrawBottom, shouldDrawTop, state)

        if (shouldDrawDivider) {
            canvas.drawLine(startX.toFloat(), startY, stopX.toFloat(), stopY, dividerPaint)
        }
    }

    private fun shouldDrawMiddleDivider(areSameHolders: Boolean): Boolean {
        return Rules.checkMiddleRule(dividerDrawingRule) && areSameHolders
    }

    private fun shouldDrawBottomDivider(areSameHolders: Boolean, isLastHolder: Boolean): Boolean {
        return Rules.checkBottomRule(dividerDrawingRule) && (!areSameHolders || isLastHolder)
    }

    private fun shouldDrawTopDivider(areSameHolders: Boolean, isFirstHolder: Boolean): Boolean {
        return Rules.checkTopRule(dividerDrawingRule) && (!areSameHolders || isFirstHolder)
    }

    private fun shouldDrawDivider(
        shouldDrawMiddleDivider: Boolean,
        shouldDrawBottomDivider: Boolean,
        shouldDrawTopDivider: Boolean,
        state: RecyclerView.State
    ): Boolean {
        return shouldDrawMiddleDivider || shouldDrawBottomDivider || shouldDrawTopDivider
    }

    private companion object {
        const val UNDEFINE_VIEW_HOLDER: Int = -1
    }
}