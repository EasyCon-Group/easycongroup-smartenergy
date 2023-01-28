package uz.easycongroup.smartenergy.domain.data.models.dashboard.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class DeliveryType(
    val id: Long,
    val address: String
) : Parcelable