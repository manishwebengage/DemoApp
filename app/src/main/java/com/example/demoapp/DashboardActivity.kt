package com.example.demoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoapp.databinding.ActivityDashboardBinding
import com.webengage.sdk.android.User
import com.webengage.sdk.android.WebEngage

class DashboardActivity: Activity() {

    lateinit var binding: ActivityDashboardBinding
    private lateinit var weUser : User
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard)
        init()
        setContentView(binding.root)
    }

    private fun init(){
        if(intent != null){
            userName = intent.getStringExtra("UserName").toString()
        }
        binding.nameText.text = userName
        weUser = WebEngage.get().user()
        binding.logoutButton.setOnClickListener{
            weUser.logout()
            SharedPref.writeCuidToSharedPreferences(this,"")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.button1.setOnClickListener {
            startActivity(Intent(this, NoActionBarNoStatusBarActivity::class.java))
        }
        binding.button2.setOnClickListener {
            startActivity(Intent(this, ActionbarActivity::class.java))
        }
        binding.button3.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val weAnalytics = WebEngage.get().analytics()
        weAnalytics.screenNavigated("dashboard")
    }
}