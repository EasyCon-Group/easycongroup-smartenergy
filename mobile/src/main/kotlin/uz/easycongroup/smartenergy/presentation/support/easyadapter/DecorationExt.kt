package uz.easycongroup.smartenergy.presentation.support.easyadapter

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Px
import ru.surfstudio.android.recycler.decorator.Decorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator.HorizontalLinearDividerDecorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.decorator.LinearDividerDecorator
import uz.easycongroup.smartenergy.presentation.support.easyadapter.gap.Gap
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.DividerRule
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.Rules
import uz.easycongroup.smartenergy.presentation.utils.toPx

fun linearDividerDecorator(
    @ColorInt color: Int = Color.TRANSPARENT,
    @Px paddingStart: Int = 0,
    @Px paddingEnd: Int = 0,
    @Px height: Int = 1.toPx,
    @DividerRule rule: Int = Rules.MIDDLE or Rules.BOTTOM
): Decorator.ViewHolderDecor {
    val gap = Gap(
        color = color,
        paddingStart = paddingStart,
        paddingEnd = paddingEnd,
        height = height,
        rule = rule
    )
    return LinearDividerDecorator(gap)
}

fun horizontalLinearDividerDecorator(
    @ColorInt color: Int = Color.TRANSPARENT,
    @Px paddingStart: Int = 0,
    @Px paddingEnd: Int = 0,
    @Px height: Int = 1.toPx,
    @DividerRule rule: Int = Rules.MIDDLE or Rules.BOTTOM
): Decorator.ViewHolderDecor {
    val gap = Gap(
        color = color,
        paddingStart = paddingStart,
        paddingEnd = paddingEnd,
        height = height,
        rule = rule
    )
    return HorizontalLinearDividerDecorator(gap)
}