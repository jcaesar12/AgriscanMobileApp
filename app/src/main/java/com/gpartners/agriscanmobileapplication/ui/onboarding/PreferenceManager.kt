package com.gpartners.agriscanmobileapplication.ui.onboarding

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("onboarding_prefs", Context.MODE_PRIVATE)

    fun setOnboardingSeen(seen: Boolean) {
        prefs.edit().putBoolean("onboarding_seen", seen).apply()
    }

    fun hasSeenOnboarding(): Boolean {
        return prefs.getBoolean("onboarding_seen", false)
    }
}