package uz.easycongroup.smartenergy.data.models.category.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogCategoryResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("parent_id")
    val parentId: Long,

    @SerialName("is_main")
    val isMain: Boolean? = null,

    @SerialName("num")
    val num: Int? = null,

    @SerialName("icon")
    val imageUrlOrId: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("amount")
    val productCount: Int? = null
)