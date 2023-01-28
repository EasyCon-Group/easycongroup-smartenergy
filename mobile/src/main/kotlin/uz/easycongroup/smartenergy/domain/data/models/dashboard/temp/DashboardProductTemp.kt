package uz.easycongroup.smartenergy.domain.data.models.dashboard.temp

data class DashboardProductTemp(
    val id: Long,
    val title: String,
    val realPrice: String,
    val actualPrice: String,
    val discountPercent: Int,
    val currency: String,
    val cartQuantity: Int,
    val imageUrl: String,
    val ratingAvg: Double,
    val ratingCount: Int
) {
    val hasDiscount: Boolean = discountPercent > 0

    val isAddedInCart: Boolean
        get() = cartQuantity > 0
}