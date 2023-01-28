package uz.easycongroup.smartenergy.domain.data.models.order.product

import java.math.BigDecimal

data class OrderProduct(
    val id: Long,
    val orderId: Long,
    val productId: Int,
    val productTitle: String? = null,
    val productImage: String? = null,
    val price: BigDecimal? = null,
    val totalPrice: BigDecimal? = null,
    val quantity: Int,
)