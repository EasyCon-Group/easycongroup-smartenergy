package uz.easycongroup.smartenergy.core.data.models.pagination

import ru.surfstudio.android.datalistpagecount.domain.datalist.DataList
import ru.surfstudio.android.easyadapter.pagination.PaginationState

/**
 * UI representation of paginated `DataList`.
 */
data class PaginationBundle<T>(
    val data: DataList<T>? = null,
    val state: PaginationState? = null
) {

    val hasData = !data.isNullOrEmpty()

    /**
     * Check that `data` and `state` exist and if so - execute `callback`,
     * do nothing otherwise.
     * */
    fun safeGet(callback: (DataList<T>, PaginationState) -> Unit) {
        callback(data ?: return, state ?: return)
    }

    /**
     * Transform type of `PaginationBundle` and it's data.
     */
    fun <R> transform(action: (T) -> R): PaginationBundle<R> {
        return PaginationBundle(data?.transform(action), state)
    }
}