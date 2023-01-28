package uz.easycongroup.smartenergy.data.models.auth.confirmation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserResponse(

//{
//    "error": null,
//    "message": null,
//    "timestamp": "18.01.2023 151336",
//    "status": 200,
//    "path": null,
//    "data":
//    {
//        "user": {
//        "token": "f4b23d3f-96fa-4676-b537-aa586921563a",
//        "projectId": 30,
//        "user": {
//        "pinfl": 32602902620035,
//        "authSource": "project",
//        "offerInfo": false,
//        "eimzoAllowToLogin": "all",
//        "locale": "uz",
//        "type": "1",
//        "orgId": 32602902620035,
//        "updateId": 327636214,
//        "orgType": null,
//        "clientType": null,
//        "messageType": "sms,telegram,email",
//        "headerPassportNumber": "AA9731934",
//        "permissions": [
//        "eimzo.user"
//        ],
//        "postName": "Жисмоний шахс",
//        "tin": 0,
//        "theme": null,
//        "id": 1165999,
//        "state": 1,
//        "email": "urokovmuzaffar2000@gmail.com",
//        "address": "Қашқадарё вилояти, Муборак тумани, , А.Темур МФЙ,Гулшан, 2",
//        "level": null,
//        "photo": null,
//        "fullName": "NORQULOV SAYFULLA RAJABBOYEVICH",
//        "sellerInfo": false,
//        "telegramId": 1171473777,
//        "headerBirthDate": null,
//        "sourceUpdateId": null,
//        "registeredWithEimzo": true,
//        "areaId": 1010,
//        "akey": null,
//        "mobilePhone": "998935547520",
//        "hasBind": false,
//        "typeActivity": "PHYSICAL",
//        "mfo": "00187",
//        "backupMobilePhone": null,
//        "projectId": 30,
//        "oblId": 10,
//        "loggingLevel": "OFF",
//        "account": null,
//        "username": "user32602902620035"
//    }
//    },
//        "mobile": {
//        "night_mode": null
//    }
//    }
//}
    @SerialName("pinfl")
    val pinfl: Long,

    @SerialName("authSource")
    val authSource: String,

    @SerialName("offerInfo")
    val offerInfo: Boolean,

    @SerialName("eimzoAllowToLogin")
    val edsAllowToLogin: String,

    @SerialName("locale")
    val locale: String,

    @SerialName("type")
    val type: String,

    @SerialName("orgId")
    val orgId: Long,

    @SerialName("updateId")
    val updateId: Int,

    @SerialName("orgType")
    val orgType: String? = null,

    @SerialName("clientType")
    val clientType: String? = null,

    @SerialName("messageType")
    val messageType: String,

    @SerialName("headerPassportNumber")
    val headerPassportNumber: String,

    @SerialName("permissions")
    val permissions: List<String>,

    @SerialName("postName")
    val postName: String,

    @SerialName("tin")
    val tin: Int,

    @SerialName("theme")
    val theme: String? = null,

    @SerialName("id")
    val id: Int,

    @SerialName("state")
    val state: Int,

    @SerialName("email")
    val email: String,

    @SerialName("address")
    val address: String,

    @SerialName("level")
    val level: String? = null,

    @SerialName("photo")
    val photo: String? = null,

    @SerialName("fullName")
    val fullName: String,

    @SerialName("sellerInfo")
    val sellerInfo: Boolean,

    @SerialName("telegramId")
    val telegramId: Int,

    @SerialName("headerBirthDate")
    val headerBirthDate: String? = null,

    @SerialName("sourceUpdateId")
    val sourceUpdateId: String? = null,

    @SerialName("registeredWithEimzo")
    val registeredWithEimzo: Boolean,

    @SerialName("areaId")
    val areaId: Int,

    @SerialName("akey")
    val akey: String? = null,

    @SerialName("mobilePhone")
    val mobilePhone: String,

    @SerialName("hasBind")
    val hasBind: Boolean,

    @SerialName("typeActivity")
    val typeActivity: String,

    @SerialName("mfo")
    val mfo: String,

    @SerialName("backupMobilePhone")
    val backupMobilePhone: String? = null,

    @SerialName("projectId")
    val projectId: Int,

    @SerialName("oblId")
    val oblId: Int,

    @SerialName("loggingLevel")
    val loggingLevel: String,

    @SerialName("account")
    val account: String? = null,

    @SerialName("username")
    val username: String
)