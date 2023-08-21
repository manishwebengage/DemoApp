package com.example.demoapp

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoapp.databinding.ActivityNoActionBarBinding
import com.webengage.sdk.android.WebEngage

class NoActionBarNoStatusBarActivity: Activity() {

    lateinit var binding: ActivityNoActionBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_no_action_bar)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        val weAnalytics = WebEngage.get().analytics()
        weAnalytics.screenNavigated("noActionBarScreen")
    }
}