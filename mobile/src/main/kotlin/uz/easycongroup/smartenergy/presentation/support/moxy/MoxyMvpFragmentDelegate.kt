package uz.easycongroup.smartenergy.presentation.support.moxy

import android.os.Bundle
import androidx.fragment.app.Fragment
import moxy.MvpDelegate
import java.lang.ref.WeakReference

class MoxyMvpFragmentDelegate<T : Fragment>(fragment: T) {
    private val fragmentReference: WeakReference<T> = WeakReference(fragment)
    private var stateSaved: Boolean = false
    private var delegate: MvpDelegate<out T>? = null

    fun onCreate(savedInstanceState: Bundle?) =
        mvpDelegate.onCreate(savedInstanceState)

    fun onStart() {
        stateSaved = false; mvpDelegate.onAttach()
    }

    fun onResume() {
        stateSaved = false; mvpDelegate.onAttach()
    }

    fun onSaveInstanceState(outState: Bundle) {
        stateSaved = true; mvpDelegate.let { it.onSaveInstanceState(outState); it.onDetach() }
    }

    fun onStop() =
        mvpDelegate.onDetach()

    fun onDestroyView() =
        mvpDelegate.let { it.onDetach(); it.onDestroyView() }

    fun onDestroy() {
        val fragment: Fragment? = fragmentReference.get()
        if (fragment?.requireActivity()?.isFinishing == true) {
            mvpDelegate.onDestroy(); return
        }

        if (stateSaved) {
            stateSaved = false; return
        }

        var anyParentIsRemoving = false
        var parentFragment: Fragment? = fragment?.parentFragment

        while (!anyParentIsRemoving && parentFragment != null) {
            anyParentIsRemoving = parentFragment.isRemoving
            parentFragment = parentFragment.parentFragment
        }

        if (fragment?.isRemoving == true || anyParentIsRemoving) mvpDelegate.onDestroy()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val mvpDelegate: MvpDelegate<*>
        get() {
            if (delegate == null) delegate = MvpDelegate<T>(fragmentReference.get())
            return checkNotNull(delegate)
        }
}