package uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner

/**
 * Create and add a new [OnBackPressedCallback] that calls [onBackPressed] in
 * [OnBackPressedCallback.handleOnBackPressed].
 *
 * If an [owner] is specified, the callback will only be added when the Lifecycle is
 * [androidx.lifecycle.Lifecycle.State.STARTED].
 *
 * A default [enabled] state can be supplied.
 */
internal fun OnBackPressedDispatcher.addCallback(
    owner: LifecycleOwner? = null,
    enabled: Boolean = true,
    onBackPressed: OnBackPressedCallback.() -> Unit
): OnBackPressedCallback {
    val callback = object : OnBackPressedCallback(enabled) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }
    if (owner != null) {
        addCallback(owner, callback)
    } else {
        addCallback(callback)
    }
    return callback
}