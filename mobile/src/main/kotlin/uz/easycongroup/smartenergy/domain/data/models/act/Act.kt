package uz.easycongroup.smartenergy.domain.data.models.act

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Act(
    val id: Long,
    val name: String,
    val child: MutableList<Act>? = null,
) : Parcelable {

    val hasChild: Boolean
        get() = !child.isNullOrEmpty()
}