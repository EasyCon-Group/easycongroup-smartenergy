package uz.easycongroup.smartenergy.core.presentation.utils.string

import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.text.*

inline fun SpannableStringBuilder.textColorSpan(
    @ColorInt color: Int,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = color(color, builderAction)

inline fun SpannableStringBuilder.backgroundColorSpan(
    @ColorInt color: Int,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = backgroundColor(color, builderAction)

inline fun SpannableStringBuilder.strikeThroughSpan(
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = strikeThrough(builderAction)

inline fun SpannableStringBuilder.scaleSpan(
    proportion: Float,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = scale(proportion, builderAction)

inline fun SpannableStringBuilder.superscriptSpan(
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = superscript(builderAction)

public inline fun SpannableStringBuilder.underlineSpan(
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = underline(builderAction)


inline fun SpannableStringBuilder.clickableLinkSpan(
    crossinline onClickAction: () -> Unit,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = inSpans(object : ClickableSpan() {
    override fun onClick(widget: View) {
        onClickAction.invoke()
    }
}, builderAction)

inline fun SpannableStringBuilder.clickableSpan(
    crossinline onClickAction: () -> Unit,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = inSpans(object : ClickableSpan() {
    override fun onClick(widget: View) {
        onClickAction.invoke()
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = ds.linkColor
    }
}, builderAction)