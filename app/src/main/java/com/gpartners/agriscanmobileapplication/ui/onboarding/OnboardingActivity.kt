package com.gpartners.agriscanmobileapplication.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.databinding.ActivityOnboardingBinding
import com.gpartners.agriscanmobileapplication.ui.login_and_register.LoginActivity
import com.gpartners.agriscanmobileapplication.ui.mock_data.OnboardingItem

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample slides
        val items = listOf(
            OnboardingItem(
                imageRes = R.drawable.onboardingimage,
                title = "Welcome to AgriScan",
                description = "Your smart farming companion, discover how to scan crops, get AI advice, track finances, and grow better."
            ),
            OnboardingItem(
                imageRes = R.drawable.diseasedetection,
                title = "Instant Disease Detection",
                description = "Quickly scan crops and get AI-powered diagnoses."
            ),
            OnboardingItem(
                imageRes = R.drawable.trackfinances,
                title = "Track Your Farm Finances",
                description = "Manage your agricultural income, expenses, and yields in KES."
            ),
            OnboardingItem(
                imageRes = R.drawable.mwanedu,
                title = "Ask Mwanedu – Your AI Assistant",
                description = "Ask questions and receive instant farming advice."
            ),
            OnboardingItem(
                imageRes = R.drawable.tailoredforyou,
                title = "Tailored For You",
                description = "Get location-based insights and crop recommendations now!"
            )
        )

        adapter = OnboardingAdapter(this, items)
        binding.viewPager.adapter = adapter

        binding.nextButton.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < items.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                // Last page → move to MainActivity (or Dashboard/Login)
                PreferenceManager(this).setOnboardingSeen(true)
                startActivity(Intent(this, LoginActivity    ::class.java))
                finish()
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }
}