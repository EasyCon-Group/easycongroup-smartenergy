package uz.easycongroup.smartenergy.core.data.models.pagination

data class PaginationData<T>(
    val data: List<T>,
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPage: Int
)