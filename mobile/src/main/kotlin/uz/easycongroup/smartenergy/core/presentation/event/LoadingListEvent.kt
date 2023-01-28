package uz.easycongroup.smartenergy.core.presentation.event

sealed class LoadingListEvent<out T> {

    object LoadingList : LoadingListEvent<Nothing>()

    data class SuccessList<out T>(val data: List<T>) : LoadingListEvent<T>()

    object EmptyList : LoadingListEvent<Nothing>()

    data class ErrorList(val message: String?) : LoadingListEvent<Nothing>()
}