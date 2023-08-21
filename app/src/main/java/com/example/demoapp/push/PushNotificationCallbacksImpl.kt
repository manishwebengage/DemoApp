package com.example.demoapp.push

import android.content.Context
import android.util.Log
import com.webengage.sdk.android.actions.render.PushNotificationData
import com.webengage.sdk.android.callbacks.PushNotificationCallbacks

class PushNotificationCallbacksImpl : PushNotificationCallbacks {
    override fun onPushNotificationReceived(
        p0: Context?,
        p1: PushNotificationData?
    ): PushNotificationData {
        Log.i("Custom Notification", p1.toString())
        return p1!!
    }

    override fun onPushNotificationShown(p0: Context?, p1: PushNotificationData?) {
        return
    }

    override fun onPushNotificationClicked(p0: Context?, p1: PushNotificationData?): Boolean {
        return true
    }

    override fun onPushNotificationDismissed(p0: Context?, p1: PushNotificationData?) {
        return
    }

    override fun onPushNotificationActionClicked(
        p0: Context?,
        p1: PushNotificationData?,
        p2: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

}
