package uz.easycongroup.smartenergy.domain.data.models.dashboard.catalog

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import uz.easycongroup.smartenergy.domain.data.models.dashboard.common.DeliveryType
import uz.easycongroup.smartenergy.domain.data.models.dashboard.common.ProductSeller

@Parcelize
data class CatalogProduct(
    val id: Long,
    val name: String,
    val unitId: Long,
    val categoryId: Long,
    val minimumRequiredQuantityToBuy: Int,
    val organizationId: Long,
    val hasDiscount: Boolean,
    val price: Double,
    val oldPrice: Double?,
    val isDeliveryAvailable: Boolean,
    val deliveryTypes: List<DeliveryType>,
    val seller: ProductSeller,
    val rating: Double,
    val reviewCount: Int,
    val cartQuantity: Int,
    var isFavorite: Boolean,
    var isRecentlyAdded: Boolean,
    val viewCount: Long,
    val productPublicationOwner: String,
    val images: List<String>
) : Parcelable {

    @IgnoredOnParcel
    val mainImageUrl
        get() = images.firstOrNull() ?: ""

    @IgnoredOnParcel
    val currency: String = "uzs"

    @IgnoredOnParcel
    val discountPercent: Int
        get() = if (oldPrice != null) ((price / oldPrice) * 100).toInt() else 0

    @IgnoredOnParcel
    val isAddedInCart: Boolean
        get() = cartQuantity > 0
}