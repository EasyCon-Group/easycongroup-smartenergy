package uz.easycongroup.smartenergy.domain.data.models.order.list

data class Order(
    val id: Long,
    val title: String,
    val status: String,
    val price: String,
    val currency: String,
    val date: String,
    val imageUrl: String,
    val isDeliveryFeedbackRequired: Boolean
)