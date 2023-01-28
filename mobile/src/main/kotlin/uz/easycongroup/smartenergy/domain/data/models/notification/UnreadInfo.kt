package uz.easycongroup.smartenergy.domain.data.models.notification

data class UnreadInfo(
    val count: Long,
    val messages: List<String>
)