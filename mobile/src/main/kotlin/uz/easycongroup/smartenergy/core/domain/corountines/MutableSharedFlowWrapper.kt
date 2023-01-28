package uz.easycongroup.smartenergy.core.domain.corountines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

@Suppress("EXPERIMENTAL_API_USAGE", "EXPERIMENTAL_OVERRIDE")
open class MutableSharedFlowWrapper<T>(
    replay: Int = 0,
    extraBufferCapacity: Int = 2,
    onBufferOverflow: BufferOverflow = BufferOverflow.DROP_LATEST,
    private val mutableSharedFlow: MutableSharedFlow<T> =
        MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)
) : MutableSharedFlow<T> {

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        mutableSharedFlow.collect(collector)
    }

    override val subscriptionCount: StateFlow<Int>
        get() = mutableSharedFlow.subscriptionCount

    override suspend fun emit(value: T) {
        mutableSharedFlow.emit(value)
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        mutableSharedFlow.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        return mutableSharedFlow.tryEmit(value)
    }

    override val replayCache: List<T>
        get() = mutableSharedFlow.replayCache

    val sharedFlow: SharedFlow<T>
        get() = mutableSharedFlow.asSharedFlow()
}