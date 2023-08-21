package com.example.demoapp

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoapp.databinding.ActivityThemeBinding
import com.webengage.sdk.android.WebEngage

class ThemeActivity: Activity() {

    private lateinit var binding: ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_theme)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        val weAnalytics = WebEngage.get().analytics()
        weAnalytics.screenNavigated("themeScreen")
    }
}