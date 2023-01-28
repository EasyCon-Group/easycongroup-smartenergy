package uz.easycongroup.smartenergy.domain.data.models.dashboard.detail

import kotlinx.parcelize.IgnoredOnParcel
import uz.easycongroup.smartenergy.domain.data.models.dashboard.common.DeliveryType
import uz.easycongroup.smartenergy.domain.data.models.dashboard.common.ProductSeller

data class ProductDetail(
    val id: Long,
    val name: String,
    val categoryId: Long,
    val unitId: Long,
    val unit: String,
    val makeName: String,
    val countryName: String,
    val countryId: Long,
    val price: Long,
    val amount: Int,
    val minAmount: Int,
    val expirationLife: String,
    val freeServiceLife: String,
    val year: Int,
    val technicalParameters: String,
    val images: List<String>,
    val maxAmount: Int,
    val organizationId: Int,
    val rating: ProductRating,
    val sourceClient: String,
    val soldAmount: Int,
    val seller: ProductSeller,
    val isDeliveryAvailable: Boolean,
    val deliveryTypes: List<DeliveryType>,
    val warehouses: List<ProductWarehouse>
){

    @IgnoredOnParcel
    val hasDiscount = false

    @IgnoredOnParcel
    val mainImageUrl = images.firstOrNull() ?: ""

    @IgnoredOnParcel
    val currency: String = "uzs"

    @IgnoredOnParcel
    val discountPercent: Int = 0 // if (oldPrice != null) ((price / oldPrice) * 100).toInt() else 0

    @IgnoredOnParcel
    val isAddedInCart: Boolean
        get() = false // cartQuantity > 0
}