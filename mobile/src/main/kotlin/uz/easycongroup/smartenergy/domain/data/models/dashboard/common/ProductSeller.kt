package uz.easycongroup.smartenergy.domain.data.models.dashboard.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ProductSeller(
    val id: Long,
    val regionId: Long,
    val districtId: Long,
    val name: String,
    val address: String,
    val headerName: String,
    val mobilePhone: String,
    val email: String?
) : Parcelable