package uz.easycongroup.smartenergy.data.models.category.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MainCategoryResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val imageUrlOrId: String
)