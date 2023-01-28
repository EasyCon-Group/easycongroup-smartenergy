package uz.easycongroup.smartenergy.core.presentation.customview.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.view.updateLayoutParams
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.R.styleable.*
import uz.easycongroup.smartenergy.presentation.utils.toPx

abstract class CustomIconIndicator : LinearLayout {

    protected open var dotBetweenMargin = 6.toPx

    protected open var notSelectedDotWidth = 8.toPx
    protected open var notSelectedDotHeight = 8.toPx

    protected open var selectedDotWidth = 14.toPx
    protected open var selectedDotHeight = 8.toPx

    @DrawableRes
    protected var dotSelectedIconResId = 0

    @DrawableRes
    protected var dotNotSelectedIconResId = 0

    var indicatorOrientation = HORIZONTAL

    var indicatorGravity = CENTER

    private var indicatorCreatedListener: IndicatorCreatedListener? = null

    protected var lastPosition = -1

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        handleTypedArray(context, attrs)
    }

    private fun handleTypedArray(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIconIndicator)
        with(typedArray) {
            notSelectedDotWidth =
                getDimensionPixelSize(CustomIconIndicator_cii_dot_not_selected_width, 10.toPx)
            notSelectedDotHeight =
                getDimensionPixelSize(CustomIconIndicator_cii_dot_not_selected_height, 10.toPx)

            selectedDotWidth =
                getDimensionPixelSize(CustomIconIndicator_cii_dot_selected_width, 10.toPx)
            selectedDotHeight =
                getDimensionPixelSize(CustomIconIndicator_cii_dot_selected_height, 10.toPx)

            dotBetweenMargin =
                getDimensionPixelSize(CustomIconIndicator_cii_dots_between_margin, 10.toPx)

            dotSelectedIconResId =
                getResourceId(
                    CustomIconIndicator_cii_dot_selected_icon,
                    R.drawable.ic_indicator_dot_selected
                )

            dotNotSelectedIconResId =
                getResourceId(
                    CustomIconIndicator_cii_dot_not_selected_icon,
                    R.drawable.ic_indicator_dot_not_selected
                )

            indicatorOrientation = getInt(CustomIconIndicator_cii_orientation, VERTICAL)
            indicatorGravity = getInt(CustomIconIndicator_cii_gravity, CENTER)
        }

        typedArray.recycle()
    }

    interface IndicatorCreatedListener {
        /**
         * IndicatorCreatedListener
         *
         * @param view internal indicator view
         * @param position position
         */
        fun onIndicatorCreated(view: View?, position: Int)
    }

    fun setIndicatorCreatedListener(listener: IndicatorCreatedListener?) {
        indicatorCreatedListener = listener
    }

    fun createIndicators(count: Int, currentPosition: Int) {

        // Diff View
        val childViewCount: Int = childCount
        if (count < childViewCount) {
            removeViews(count, childViewCount - count)
        } else if (count > childViewCount) {
            val addCount = count - childViewCount
            val orientation: Int = orientation
            for (i in 0 until addCount) {
                addIndicator(orientation)
            }
        }

        // Bind Style
        var indicator: View
        for (i in 0 until count) {
            indicator = getChildAt(i)

            if (currentPosition == i) {
                indicator.setBackgroundResource(dotSelectedIconResId)
                indicator.updateLayoutParams {
                    width = selectedDotWidth
                    height = selectedDotHeight
                }
            } else {
                indicator.setBackgroundResource(dotNotSelectedIconResId)
                indicator.updateLayoutParams {
                    width = notSelectedDotWidth
                    height = notSelectedDotHeight
                }
            }

            if (indicatorCreatedListener != null) {
                indicatorCreatedListener?.onIndicatorCreated(indicator, i)
            }
        }
        lastPosition = currentPosition
    }

    protected fun addIndicator(orientation: Int) {
        val indicator = View(context)
        val params: LayoutParams = generateDefaultLayoutParams()

        params.width = notSelectedDotWidth
        params.height = notSelectedDotHeight

        if (orientation == HORIZONTAL) {
            params.leftMargin = dotBetweenMargin / 2
            params.rightMargin = dotBetweenMargin / 2
        } else {
            params.topMargin = dotBetweenMargin / 2
            params.bottomMargin = dotBetweenMargin / 2
        }

        addView(indicator, params)
    }

    open fun animatePageSelected(position: Int) {
        if (lastPosition == position) return

        if (lastPosition >= 0) {
            val currentIndicator: View? = getChildAt(lastPosition)
            currentIndicator?.setBackgroundResource(dotNotSelectedIconResId)
            currentIndicator?.updateLayoutParams {
                width = notSelectedDotWidth
                height = notSelectedDotHeight
            }
        }

        val selectedIndicator: View? = getChildAt(position)
        selectedIndicator?.setBackgroundResource(dotSelectedIconResId)
        selectedIndicator?.updateLayoutParams {
            width = selectedDotWidth
            height = selectedDotHeight
        }

        lastPosition = position
    }
}