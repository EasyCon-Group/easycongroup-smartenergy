package uz.easycongroup.smartenergy.core.domain.corountines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

open class MutableStateFlowWrapper<T>(
    initialValue: T,
    private val mutableStateFlow: MutableStateFlow<T> = MutableStateFlow(initialValue),
) : MutableStateFlow<T> {

    override var value: T
        get() = mutableStateFlow.value
        set(value) {
            mutableStateFlow.value = value
        }

    override fun compareAndSet(expect: T, update: T): Boolean {
        return expect == update
    }

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        mutableStateFlow.collect(collector)
    }

    override val subscriptionCount: StateFlow<Int>
        get() = mutableStateFlow.subscriptionCount

    override suspend fun emit(value: T) {
        mutableStateFlow.emit(value)
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        mutableStateFlow.resetReplayCache()
    }

    override fun tryEmit(value: T): Boolean {
        return mutableStateFlow.tryEmit(value)
    }

    override val replayCache: List<T>
        get() = mutableStateFlow.replayCache

    val stateFlow: SharedFlow<T>
        get() = mutableStateFlow.asStateFlow()
}