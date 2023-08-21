package com.example.demoapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.net.UrlQuerySanitizer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.demoapp.databinding.ActivityMainBinding
import com.webengage.sdk.android.Analytics
import com.webengage.sdk.android.User
import com.webengage.sdk.android.WebEngage
import java.net.URI
import java.net.URL


class MainActivity : Activity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var weUser : User
    private lateinit var weAnalytics : Analytics
    private val PUSH_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        init()
        setContentView(binding.root)
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
        if(appLinkData != null) {
            val sanitizer = UrlQuerySanitizer()
            sanitizer.allowUnregisteredParamaters = true
            sanitizer.parseUrl(appLinkData.toString())
            val screen = sanitizer.getValue("screen")
            Log.d("DEEPLINK_URL",appLinkData.toString()+" : "+screen)
        }

        appLinkData?.path
    }

    private fun init(){
        weUser = WebEngage.get().user()
        if(!SharedPref.getCuidFromSharedPrefs(this).isNullOrEmpty()){
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("UserName",SharedPref.getCuidFromSharedPrefs(this))
            startActivity(intent)
        }
        binding.loginButton.setOnClickListener{
            if(binding.nameEditText.text.toString().isNotEmpty()){
                loginUser()
            }
        }
    }

    private fun loginUser(){
        weUser.login(binding.nameEditText.text.toString())
        SharedPref.writeCuidToSharedPreferences(this,binding.nameEditText.text.toString())
        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("UserName",binding.nameEditText.text.toString())
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkSelfPermission(PUSH_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            WebEngage.get().user().setDevicePushOptIn(true)
        }
    }

    override fun onStart() {
        super.onStart()
        requestPermission()
        val weAnalytics = WebEngage.get().analytics()
        weAnalytics.screenNavigated("login")
    }

    private fun requestPermission() {
        //For App's targeting below 33
        if (Build.VERSION.SDK_INT >= 33) {
            if (checkSelfPermission(PUSH_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(PUSH_NOTIFICATIONS),
                    102
                )
                WebEngage.get().user().setDevicePushOptIn(false)
            } else {
                WebEngage.get().user().setDevicePushOptIn(true)
            }
        }
    }


}