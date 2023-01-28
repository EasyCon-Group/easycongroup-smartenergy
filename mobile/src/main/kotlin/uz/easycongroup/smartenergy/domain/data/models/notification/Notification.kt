package uz.easycongroup.smartenergy.domain.data.models.notification

data class Notification(
    val id: Long,
    val date: String,
    val message: String,
    val image: String
)