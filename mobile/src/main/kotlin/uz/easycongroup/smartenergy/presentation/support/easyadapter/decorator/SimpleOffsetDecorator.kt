package uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.surfstudio.android.recycler.decorator.Decorator

class SimpleOffsetDecorator(
    private val left: Int = 0,
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0
) : Decorator.OffsetDecor {

    constructor(leftRight: Int, topBottom: Int) :
            this(left = leftRight, top = topBottom, right = leftRight, bottom = topBottom)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(left, top, right, bottom)
    }
}