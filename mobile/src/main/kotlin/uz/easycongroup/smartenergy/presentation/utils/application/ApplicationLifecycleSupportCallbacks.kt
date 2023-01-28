package uz.easycongroup.smartenergy.presentation.utils.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import uz.easycongroup.smartenergy.presentation.utils.hideSoftKeyboard

class ApplicationLifecycleSupportCallbacks(application: Application) {

    init {
        application.registerActivityLifecycleCallbacks(SupportActivityLifecycleCallbacks)
    }

    private object SupportActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivityStarted(activity: Activity) {}

        override fun onActivityDestroyed(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityStopped(activity: Activity) {}

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is FragmentActivity)
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                    SupportFragmentLifecycleCallbacks,
                    true
                )
        }

        override fun onActivityResumed(activity: Activity) {}
    }

    private object SupportFragmentLifecycleCallbacks :
        FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentCreated(
            fm: FragmentManager,
            fragment: Fragment,
            savedInstanceState: Bundle?
        ) {
            fragment.childFragmentManager.registerFragmentLifecycleCallbacks(
                SupportFragmentLifecycleCallbacks,
                true
            )
        }

        override fun onFragmentPaused(fm: FragmentManager, fragment: Fragment) {
            fragment.hideSoftKeyboard()
        }
    }


}