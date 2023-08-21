package com.example.demoapp.push

import android.content.Context
import android.os.Bundle
import com.webengage.sdk.android.actions.render.PushNotificationData

class MyPushRenderer:
    com.webengage.sdk.android.callbacks.CustomPushRender,
    com.webengage.sdk.android.callbacks.CustomPushRerender {
    override fun onRender(context: Context, pushNotificationData: PushNotificationData): Boolean {
        return true
    }

    override fun onRerender(
        context: Context,
        pushNotificationData: PushNotificationData,
        extras: Bundle
    ): Boolean {
        return true
    }
}