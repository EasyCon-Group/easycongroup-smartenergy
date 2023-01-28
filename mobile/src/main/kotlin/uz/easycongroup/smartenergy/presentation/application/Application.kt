package uz.easycongroup.smartenergy.presentation.application

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber
import timber.log.Timber.DebugTree
import uz.easycongroup.smartenergy.BuildConfig
import uz.easycongroup.smartenergy.presentation.application.di.ApplicationDaggerComponent
import uz.easycongroup.smartenergy.presentation.utils.application.ApplicationLifecycleSupportCallbacks
import kotlin.properties.Delegates

internal class Application : Application() {

    var pureContext: Context by Delegates.notNull()
        private set

    lateinit var applicationDaggerComponent: ApplicationDaggerComponent
        private set

    override fun attachBaseContext(base: Context) {
        ApplicationDaggerComponent
            .create(base)
            .also { applicationDaggerComponent = it }
            .inject(this)

        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.LOG_ENABLED) {
            Timber.plant(DebugTree())
        }
        Fresco.initialize(this)
        ApplicationLifecycleSupportCallbacks(this)
    }
}