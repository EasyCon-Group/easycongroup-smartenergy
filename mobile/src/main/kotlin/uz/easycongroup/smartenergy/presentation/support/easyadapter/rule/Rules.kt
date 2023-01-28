package uz.easycongroup.smartenergy.presentation.support.easyadapter.rule

/**
 * Правила для отрисовки разделителей между элементами RecyclerView.
 */
object Rules {

    const val MIDDLE = 1
    const val BOTTOM = 2
    const val TOP = 4

    fun checkMiddleRule(rule: Int): Boolean {
        return rule and MIDDLE != 0
    }

    fun checkBottomRule(rule: Int): Boolean {
        return rule and BOTTOM != 0
    }

    fun checkTopRule(rule: Int): Boolean {
        return rule and TOP != 0
    }

    fun checkAllRule(rule: Int): Boolean {
        return rule and (MIDDLE or BOTTOM or TOP) != 0
    }
}