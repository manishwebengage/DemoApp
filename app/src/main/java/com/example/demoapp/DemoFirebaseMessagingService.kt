package com.example.demoapp

import android.text.TextUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.webengage.sdk.android.WebEngage

class DemoFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data = message.data
        if (data.containsKey("source") && "webengage" == data["source"]) {
            WebEngage.get().receive(data)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        if (!TextUtils.isEmpty(token)){
            WebEngage.get().setRegistrationID(token)
        }

    }
}