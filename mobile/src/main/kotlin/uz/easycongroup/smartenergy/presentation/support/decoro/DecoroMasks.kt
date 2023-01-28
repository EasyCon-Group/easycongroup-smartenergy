package uz.easycongroup.smartenergy.presentation.support.decoro

import android.widget.TextView
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.watchers.MaskFormatWatcher
import uz.easycongroup.smartenergy.presentation.support.decoro.DecoroMasks.MaskType.*

object DecoroMasks {

    enum class MaskType {
        MASK_TIN, MASK_PHONE_UZB, MASK_DATE
    }

    fun install(maskType: MaskType, view: TextView) {
        when (maskType) {
            MASK_TIN -> tinMaskInstallOn(view)
            MASK_PHONE_UZB -> phoneMaskInstallOn(view)
            MASK_DATE -> dateMaskInstallOn(view)
        }
    }

    private fun phoneMaskInstallOn(view: TextView) {
        val phoneSlots = arrayOf(
//            PredefinedSlots.hardcodedSlot('+'),
//            PredefinedSlots.hardcodedSlot('9'),
//            PredefinedSlots.hardcodedSlot('9'),
//            PredefinedSlots.hardcodedSlot('8'),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
//            PredefinedSlots.hardcodedSlot('(').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit()
        )
        val mask = MaskImpl.createTerminated(phoneSlots)
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(view)
    }

    private fun tinMaskInstallOn(view: TextView) {
        val tinSlots = arrayOf(
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit()
        )
        val mask = MaskImpl.createTerminated(tinSlots)
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(view)
    }

    private fun dateMaskInstallOn(view: TextView) {
        val tinSlots = arrayOf(
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
        )
        val mask = MaskImpl.createTerminated(tinSlots)
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(view)
    }

}