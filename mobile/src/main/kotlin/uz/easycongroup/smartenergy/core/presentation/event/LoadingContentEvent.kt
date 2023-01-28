package uz.easycongroup.smartenergy.core.presentation.event

sealed class LoadingContentEvent<out T> {

    object LoadingContent : LoadingContentEvent<Nothing>()

    data class SuccessContent<out T>(val data: T) : LoadingContentEvent<T>()

    data class ErrorContent(val message: String?) : LoadingContentEvent<Nothing>()
}