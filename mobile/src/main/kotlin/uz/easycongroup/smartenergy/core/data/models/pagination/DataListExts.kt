package uz.easycongroup.smartenergy.core.data.models.pagination

import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList

fun <T> PaginationData<T>.mapToDataList(): DataList<T> =
    DataList(
        /* data = */ data,
        /* page = */ page,
        /* pageSize = */ perPage,
        /* totalItemsCount = */ total,
        /* totalPagesCount = */ totalPage
    )

fun <T> PaginationData<T>.mapToList(): List<T> =
    data


fun <T> List<T>.mapToPaginationData(
    page: Int,
    perPage: Int,
    total: Int = DataList.UNSPECIFIED_TOTAL_ITEMS_COUNT,
    totalPage: Int = DataList.UNSPECIFIED_TOTAL_PAGES_COUNT
): PaginationData<T> =
    PaginationData(
        data = this,
        page = page,
        perPage = perPage,
        total = total,
        totalPage = totalPage
    )


fun <T> List<T>.mapToDataList(page: Int): DataList<T> =
    DataList(this, page, this.size)
