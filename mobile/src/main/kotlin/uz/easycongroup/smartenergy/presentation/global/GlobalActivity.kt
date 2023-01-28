package uz.easycongroup.smartenergy.presentation.global

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import dagger.Lazy
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import uz.easycongroup.smartenergy.BuildConfig
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.ActivityGlobalBinding
import uz.easycongroup.smartenergy.presentation.application.Application
import uz.easycongroup.smartenergy.presentation.global.di.GlobalDaggerComponent
import uz.easycongroup.smartenergy.presentation.global.router.GlobalRouter
import uz.easycongroup.smartenergy.presentation.support.cicerone.navigator.DefaultCiceroneNavigator
import uz.easycongroup.smartenergy.presentation.utils.showDefaultAlertDialog
import uz.easycongroup.smartenergy.presentation.utils.showWarningAlertDialog
import javax.inject.Inject

internal class GlobalActivity : MvpAppCompatActivity(), GlobalView {

    @Inject
    lateinit var lazyPresenter: Lazy<GlobalPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    @Inject
    lateinit var cicerone: Cicerone<GlobalRouter>
    private val navigatorHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    private val navigator by lazy { DefaultCiceroneNavigator(this, binding.frameLayout.id) }

    lateinit var globalDaggerComponent: GlobalDaggerComponent
        private set

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            if (!isGranted.containsValue(false)) {
            } else showAlertOnPermissionNotGranted()
        }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    showAlertOnPermissionAskNotAvailable()
                }
                else -> {
                    launchPermissionRequest()
                }
            }
        }
    }

    private fun launchPermissionRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.INTERNET
                )
            )
        }
    }

    private fun showAlertOnPermissionAskNotAvailable() {
        showWarningAlertDialog(
            title = getString(R.string.message_title_warning),
            message = getString(R.string.notification_permission_denied_title),
            positiveButtonTitle = getString(R.string.open_settings),
            negativeButtonTitle = getString(R.string.close),
            onPositiveButtonClicked = {
                openAppSettings()
                it.dismiss()
            },
            onNegativeButtonClicked = { it.dismiss() }
        )
    }

    private fun showAlertOnPermissionNotGranted() {
        showDefaultAlertDialog(
            title = getString(R.string.message_title_warning),
            message = getString(R.string.notification_permission_denied_title),
            positiveButtonTitle = getString(R.string.grant_permission),
            negativeButtonTitle = getString(R.string.skip),
            onPositiveButtonClicked = {
                it.dismiss()
                launchPermissionRequest()
            },
            onNegativeButtonClicked = { it.dismiss() }
        )
    }

    private fun openAppSettings() {
        startActivity(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
        })
    }

    private val binding by lazy { ActivityGlobalBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application)
            .applicationDaggerComponent
            .globalDaggerComponent
            .create()
            .also { globalDaggerComponent = it }
            .inject(this)

        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)

        askNotificationPermission()
    }

    override fun dispatchTouchEvent(motionEvent: MotionEvent?): Boolean {
        if (motionEvent?.action == MotionEvent.ACTION_DOWN && currentFocus != null) {
            runCatching { window.decorView.clearFocus() }
        }
        return super.dispatchTouchEvent(motionEvent)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}