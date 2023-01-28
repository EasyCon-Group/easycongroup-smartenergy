package uz.easycongroup.smartenergy.core.data.models.selectable

/**
 * this class used for showing selectable list items
 * */

data class SelectionItem<out T>(
    val data: T,
    var isSelected: Boolean // FIXME: is selected make val
)