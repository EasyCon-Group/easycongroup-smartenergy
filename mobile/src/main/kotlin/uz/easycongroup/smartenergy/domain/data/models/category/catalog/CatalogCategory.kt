package uz.easycongroup.smartenergy.domain.data.models.category.catalog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogCategory(
    val id: Long,
    val name: String,
    val parentId: Long,
    val isMain: Boolean,
    val num: Int,
    val imageUrlOrId: String,
    val type: String,
    val productCount: Int
) : Parcelable