package uz.easycongroup.smartenergy.domain.data.models.category.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainCategory(
    val id: Long,
    val imageUrlOrId: String,
    val name: String
) : Parcelable