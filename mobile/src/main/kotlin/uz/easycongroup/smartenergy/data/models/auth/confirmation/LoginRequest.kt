package uz.easycongroup.smartenergy.data.models.auth.confirmation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(

    // {"doc_num":"1072848","doc_date":"2022-08-25"}

    @SerialName("doc_num")
    val documentNumber: String,

    @SerialName("doc_date")
    val documentDate: String
)