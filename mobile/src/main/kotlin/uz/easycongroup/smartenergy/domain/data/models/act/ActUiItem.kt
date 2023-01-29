package uz.easycongroup.smartenergy.domain.data.models.act

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActUiItem(
    val id: Long,
    val region: Act,
    val child: MutableList<ActUiItem>? = null,
    var isSelected: Boolean = false,
    var isOpened: Boolean = false,
    var step: Int = 0
) : Parcelable {

    val hasChild: Boolean
        get() = !child.isNullOrEmpty()
}