package com.gpartners.agriscanmobileapplication.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gpartners.agriscanmobileapplication.ui.mock_data.OnboardingItem

class OnboardingAdapter(
    fa: FragmentActivity,
    private val items: List<OnboardingItem>
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment.newInstance(items[position])
    }
}