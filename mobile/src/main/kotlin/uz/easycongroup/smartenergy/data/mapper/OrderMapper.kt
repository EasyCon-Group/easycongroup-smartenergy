package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.data.models.order.OrderResponse
import uz.easycongroup.smartenergy.domain.data.models.order.list.Order

internal fun OrderResponse.orderResponseToOrder(): Order = Order(
    id = id,
    title = title,
    status = status,
    price = price,
    currency = currency,
    date = date,
    imageUrl = imageUrl,
    isDeliveryFeedbackRequired = isDeliveryFeedbackRequired
)