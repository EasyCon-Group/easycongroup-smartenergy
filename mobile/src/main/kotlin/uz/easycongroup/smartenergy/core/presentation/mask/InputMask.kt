package uz.easycongroup.smartenergy.core.presentation.mask

import android.widget.EditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

object InputMask {

    fun install(
        mask: MaskFormat,
        editText: EditText,
        shouldShowEmptySlots: Boolean = false,
        isSwap: Boolean = false,
    ) {
        val slots = UnderscoreDigitSlotsParser().parseSlots(mask.format)
        val maskImpl = MaskImpl.createTerminated(slots).apply {
            isShowingEmptySlots = shouldShowEmptySlots
        }
        MaskFormatWatcher(maskImpl).apply {
            if (isSwap) {
                this.mask.clear()
            }
        }.installOn(editText)
    }

    val UZB_PHONE_FORMAT = MaskFormat(format = "+998 (__) ___ __ __")
    val UZB_PHONE_FORMAT_WITHOUT_BRACES = MaskFormat(format = "___ __ ___ __ __")
    val UZB_PHONE_FORMAT_WITHOUT_COUNTRY_CODE = MaskFormat(format = "(__) ___ __ __")
    val CARD_NUMBER_DIGITS_FORMAT_WITHOUT_LINE = MaskFormat(format = "____ ____ ____ ____")
    val CARD_NUMBER_DIGITS_FORMAT = MaskFormat(format = "____-____-____-____")
    val CARD_EXPIRY_DIGITS_FORMAT = MaskFormat(format = "__/__")
    val DOCUMENT_NUMBER_DIGITS_FORMAT = MaskFormat(format = "_____ _____ _____")
}