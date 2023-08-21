package com.example.demoapp

import android.app.Application
import android.app.NotificationManager
import android.graphics.Color
import com.google.firebase.messaging.FirebaseMessaging
import com.webengage.pushtemplates.CustomCallback
import com.webengage.sdk.android.PushChannelConfiguration
import com.webengage.sdk.android.WebEngage
import com.webengage.sdk.android.WebEngageActivityLifeCycleCallbacks
import com.webengage.sdk.android.WebEngageConfig


class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        val webEngageConfig = WebEngageConfig.Builder()
//            .setWebEngageKey("~2024bb40")
//            .setDebugMode(true) // only in development mode
//            .setSessionDestroyTime(40)
//            .build()
//        registerActivityLifecycleCallbacks(
//            WebEngageActivityLifeCycleCallbacks(
//                this,
//                webEngageConfig
//            )
//        )
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            try {
                val token: String? = task.result
                WebEngage.get().setRegistrationID(token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//        val pushChannelConfiguration =
//            PushChannelConfiguration.Builder()
//                .setNotificationChannelName("test")
//                .setNotificationChannelGroup("test-group")
//                .setNotificationChannelImportance(NotificationManager.IMPORTANCE_DEFAULT)
//                .setNotificationChannelDescription("test notification channel")
//                .setNotificationChannelSound("light")
//                .setNotificationChannelLightColor(Color.RED)
//                .setNotificationChannelVibration(true)
//                .setNotificationChannelShowBadge(true)
//                .build()

        val builder: WebEngageConfig.Builder =
            WebEngageConfig.Builder()
                .setWebEngageKey("~2024bb40")
                .setDebugMode(true)
                .setAutoGCMRegistrationFlag(false)
                .setPushSmallIcon(R.mipmap.ic_icon)
                .setPushLargeIcon(R.mipmap.ic_icon)
                .setSessionDestroyTime(40)
//                .setDefaultPushChannelConfiguration(pushChannelConfiguration)
                .setPushAccentColor(Color.GREEN)

        registerActivityLifecycleCallbacks(
            WebEngageActivityLifeCycleCallbacks(
                this,
                builder.build()
            )
        )
        WebEngage.registerInAppNotificationCallback(InAppCustomCallback())
        WebEngage.registerPushNotificationCallback(CustomPushCallback())

        WebEngage.registerCustomPushRenderCallback(CustomCallback())
        WebEngage.registerCustomPushRerenderCallback(CustomCallback())
    }
}