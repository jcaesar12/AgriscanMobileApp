package com.gpartners.agriscanmobileapplication.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.CarbonTrackerActivity
import com.gpartners.agriscanmobileapplication.ui.disease_scanner.DiseaseScannerActivity
import com.gpartners.agriscanmobileapplication.ui.mwanedu.MwaneduActivity
import com.gpartners.agriscanmobileapplication.ui.profile.ProfileActivity

class DashboardActivity : AppCompatActivity() {

    // Weather section
    private lateinit var tempValue: TextView
    private lateinit var humidityValue: TextView
    private lateinit var windValue: TextView
    private lateinit var conditionValue: TextView

    // Performance Metrics
    private lateinit var activitiesValue: TextView
    private lateinit var yieldsValue: TextView
    private lateinit var scansValue: TextView

    // Finances
    private lateinit var incomeValue: TextView
    private lateinit var expensesValue: TextView
    private lateinit var balanceValue: TextView

    // Quick Actions
    // Quick Actions
    private lateinit var cardScanPlant: CardView
    private lateinit var cardAskMwanedu: CardView
    private lateinit var cardAddActivity: CardView
    private lateinit var cardTrackFinances: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Weather bindings
        tempValue = findViewById(R.id.textWeatherTemp)
        humidityValue = findViewById(R.id.textWeatherHumidity)
        windValue = findViewById(R.id.textWeatherWind)
        conditionValue = findViewById(R.id.textWeatherCondition)

        // Performance metrics bindings
        activitiesValue = findViewById(R.id.textMetricActivities)
        yieldsValue = findViewById(R.id.textMetricYields)
        scansValue = findViewById(R.id.textMetricScans)

        // Quick Actions
        cardScanPlant = findViewById(R.id.cardQuickScanPlant)
        cardAskMwanedu = findViewById(R.id.cardQuickAskMwanedu)
        cardAddActivity = findViewById(R.id.cardQuickAddActivity)
        cardTrackFinances = findViewById(R.id.cardQuickTrackFinances)

        // Populate UI with mock data
        setupMockData()

        // Handle Quick Actions
        setupQuickActions()
    }

    private fun setupMockData() {
        // Weather Overview
        tempValue.text = "24°C"
        humidityValue.text = "65%"
        windValue.text = "12 km/h"
        conditionValue.text = "Partly Cloudy"

        // Performance Metrics
        activitiesValue.text = "12"
        yieldsValue.text = "240kg"
        scansValue.text = "8"

    }

    private fun setupQuickActions() {
        cardScanPlant.setOnClickListener {
            val intent = Intent(this, DiseaseScannerActivity::class.java)
            startActivity(intent)
        }

        cardAskMwanedu.setOnClickListener {
            val intent = Intent(this, MwaneduActivity::class.java)
            startActivity(intent)
        }

        cardAddActivity.setOnClickListener {
            val intent = Intent(this, CarbonTrackerActivity::class.java)
            startActivity(intent)
        }

        cardTrackFinances.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
