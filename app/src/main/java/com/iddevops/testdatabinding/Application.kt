package com.iddevops.testdatabinding

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import com.iddevops.common.commonModules
import com.iddevops.core.common.Constant
import com.iddevops.core.common.coreCommonModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class Application : Application(){
    private val modules = arrayListOf<Module>().apply {
        addAll(coreCommonModules)
        addAll(commonModules)
    }
    private val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        listOf(
            NotificationChannel(
                Constant.NOTIFICATION.MAIN.CHANNEL,
                Constant.NOTIFICATION.MAIN.NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
        )
    } else {
        listOf()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules)
            androidLogger(Level.INFO)
        }
        killInsecureApp()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // enable multidex
        // MultiDex.install(this)
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationChannel.forEach {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    it.apply {
                        enableLights(true)
                        setShowBadge(true)
                        setSound(
                            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                            null
                        )
                        lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    }
                )
            }
        }
    }

    // need to implement com.nekolaboratory.EmulatorDetector, com.scottyab.rootbeer.RootBeer
    private fun killInsecureApp() {

        //        when (securityCheck()) {
        //            ROOTED -> {
        //                if (BuildConfig.FLAVOR == "dev") {
        //                    shortToast(getString(R.string.error_running_in_a_rooted_device))
        //                    return
        //                }
        //            }
        //            EMULATOR -> {
        //                if (BuildConfig.FLAVOR == "dev") {
        //                    shortToast(getString(R.string.error_running_on_an_emulator))
        //                    return
        //                }
        //            }
        //            else -> return
        //        }
        //
        //        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
        //
        //            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        //
        //                /**
        //                 * Kill Application immediately when activity created
        //                 */
        //                shortToast(getString(R.string.error_device_not_safe))
        //                activity.finishAffinity()
        //            }
        //
        //            override fun onActivityStarted(activity: Activity) {
        //                // nothing to do
        //            }
        //
        //            override fun onActivityResumed(activity: Activity) {
        //                // nothing to do
        //            }
        //
        //            override fun onActivityPaused(activity: Activity) {
        //                // nothing to do
        //            }
        //
        //            override fun onActivityStopped(activity: Activity) {
        //                // nothing to do
        //            }
        //
        //            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        //                // nothing to do
        //            }
        //
        //            override fun onActivityDestroyed(activity: Activity) {
        //                // nothing to do
        //            }
        //        })
    }
}