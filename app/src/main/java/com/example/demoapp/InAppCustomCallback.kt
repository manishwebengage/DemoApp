package com.example.demoapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.util.JsonReader
import android.util.Log
import com.google.gson.Gson
import com.webengage.sdk.android.User
import com.webengage.sdk.android.actions.render.InAppNotificationData
import com.webengage.sdk.android.callbacks.InAppNotificationCallbacks
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets


class InAppCustomCallback : InAppNotificationCallbacks {
    private val tag = "CustomLogger"
    override fun onInAppNotificationPrepared(
        p0: Context?,
        p1: InAppNotificationData?
    ): InAppNotificationData {
        Log.d(tag, "prepared")
        return p1!!
    }

    override fun onInAppNotificationShown(p0: Context?, p1: InAppNotificationData?) {
        Log.d(tag, "shown")
    }

    override fun onInAppNotificationClicked(
        context: Context?,
        inAppData: InAppNotificationData?,
        actionId: String?
    ): Boolean {
        if (inAppData != null && context != null) {
            val link = getLinkFromJsonObject(inAppData.data, actionId)
            if (link?.startsWith("copy-coupon:",true) == true) {
                val clipboard: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Coupon Code Copied", link.substringAfter("copy-coupon:"))
                clipboard.setPrimaryClip(clip)
                return true
            }
        }
        return false
    }

    private fun getLinkFromJsonObject(inAppData: JSONObject?, actionId: String?): String? {
        val actionsArray = inAppData?.getJSONArray("actions")
        if (actionsArray != null && actionsArray.length() > 0) {
            var action: JSONObject?
            for (i in 0 until actionsArray.length()) {
                action = actionsArray.getJSONObject(i)
                if (action.getString("actionEId").equals(actionId)) {
                    return getDecodedLink(action.getString("actionLink"))
                }
            }
        }
        return null
    }

    private fun getDecodedLink(link: String?): String? {
        if (link != null) {
            val params = Uri.parse(link).pathSegments
            if (params.size > 1) {
                return params[1] //return the decoded link
            }
        }
        return null
    }

    override fun onInAppNotificationDismissed(p0: Context?, p1: InAppNotificationData?) {
        Log.d(tag, "dismissed")
    }
}