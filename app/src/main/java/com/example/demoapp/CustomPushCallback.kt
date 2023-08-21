package com.example.demoapp

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.webengage.sdk.android.actions.render.CallToAction
import com.webengage.sdk.android.actions.render.PushNotificationData
import com.webengage.sdk.android.callbacks.PushNotificationCallbacks

class CustomPushCallback : PushNotificationCallbacks {
    private val tag = "CustomLogger"
    override fun onPushNotificationReceived(
        p0: Context?,
        p1: PushNotificationData?
    ): PushNotificationData {
        Log.d(tag, "received")
        return p1!!
    }

    override fun onPushNotificationShown(p0: Context?, p1: PushNotificationData?) {
        Log.d(tag, "shown")
    }

    override fun onPushNotificationClicked(p0: Context?, p1: PushNotificationData?): Boolean {
        Log.d(tag, "clicked")
        return true
    }

    override fun onPushNotificationDismissed(p0: Context?, p1: PushNotificationData?) {
        Log.d(tag, "dismissed")
    }

    override fun onPushNotificationActionClicked(
        context: Context?,
        pushData: PushNotificationData?,
        actionId: String?
    ): Boolean {
        Log.d(tag, "action Clicked : " + actionId + pushData.toString())
        if (pushData != null) {
            if (context != null) {
                val callToAction: CallToAction = pushData.getCallToActionById(actionId)
                if (callToAction.action.equals("dismiss", true)) {
                    dismissNotificationWithId(context, pushData.variationId.hashCode())
                    return true
                }
            }
        }
        return false
    }

    /**
     * Dismiss the notification with the provided notification ID
     */
    private fun dismissNotificationWithId(context: Context, id: Int) {
        with(NotificationManagerCompat.from(context)) {
            this.cancel(id)
        }
    }
}