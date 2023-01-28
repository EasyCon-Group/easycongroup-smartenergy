package uz.easycongroup.smartenergy.core.data.models.pagination

import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState


fun <T> PaginationPageBundle<T>.merge(other: DataList<T>): PaginationPageBundle<T> {
    val paginationState = if (other.startPage == 1) {
        if (other.canGetMore()) {
            PaginationState.READY
        } else {
            PaginationState.COMPLETE
        }
    } else {
        if (other.merge(data).canGetMore()) {
            PaginationState.READY
        } else {
            PaginationState.COMPLETE
        }
    }
    return PaginationPageBundle(
        data = other,
        state = paginationState
    )
}