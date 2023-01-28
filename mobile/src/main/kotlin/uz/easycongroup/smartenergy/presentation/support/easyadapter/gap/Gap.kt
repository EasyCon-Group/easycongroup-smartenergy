package uz.easycongroup.smartenergy.presentation.support.easyadapter.gap

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Px
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.DividerRule
import uz.easycongroup.smartenergy.presentation.support.easyadapter.rule.Rules

class Gap(
    @ColorInt val color: Int = Color.TRANSPARENT,
    @Px val height: Int = 0,
    @Px val paddingStart: Int = 0,
    @Px val paddingEnd: Int = 0,
    @DividerRule val rule: Int = Rules.MIDDLE or Rules.BOTTOM
)