package uz.easycongroup.smartenergy.data.mapper

import uz.easycongroup.smartenergy.data.models.category.catalog.CatalogCategoryResponse
import uz.easycongroup.smartenergy.data.models.category.main.MainCategoryResponse
import uz.easycongroup.smartenergy.domain.data.models.category.catalog.CatalogCategory
import uz.easycongroup.smartenergy.domain.data.models.category.main.MainCategory

internal fun MainCategoryResponse.mainCategoryResponseToMainCategory() = MainCategory(
    id = id,
    imageUrlOrId = imageUrlOrId,
    name = name
)

internal fun CatalogCategoryResponse.catalogCategoryResponseToCatalogResponse() = CatalogCategory(
    id = id,
    name = name,
    parentId = parentId,
    isMain = isMain ?: false,
    num = num ?: 0,
    imageUrlOrId = imageUrlOrId ?: "",
    type = type ?: "",
    productCount = productCount ?: 0
)