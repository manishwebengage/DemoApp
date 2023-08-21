package com.example.demoapp

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoapp.databinding.ActivityActionBarBinding
import com.webengage.sdk.android.WebEngage

class ActionbarActivity: Activity() {

    private lateinit var binding: ActivityActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_action_bar)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        val weAnalytics = WebEngage.get().analytics()
        weAnalytics.screenNavigated("actionBarScreen")
    }
}