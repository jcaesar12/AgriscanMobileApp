package com.gpartners.agriscanmobileapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gpartners.agriscanmobileapplication.databinding.ActivitySplashBinding
import com.gpartners.agriscanmobileapplication.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Wait for 2 seconds, then go to OnboardingActivity
        lifecycleScope.launch {
            delay(2000) // 2s splash delay
            startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
            finish()
        }
    }
}