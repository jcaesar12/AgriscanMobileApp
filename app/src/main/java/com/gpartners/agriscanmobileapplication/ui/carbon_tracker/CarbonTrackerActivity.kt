package com.gpartners.agriscanmobileapplication.ui.carbon_tracker

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gpartners.agriscanmobileapplication.R
import java.util.*

class CarbonTrackerActivity : AppCompatActivity() {

    // Top metrics cards
    private lateinit var cardCarbonSaved: CardView
    private lateinit var cardReductionRate: CardView
    private lateinit var cardEcoScore: CardView

    // Activities container
    private lateinit var activitiesContainer: FrameLayout

    // RecyclerView and Adapter for activities
    private lateinit var activitiesRecyclerView: RecyclerView
    private lateinit var activitiesAdapter: CarbonActivityAdapter
    private var activitiesList = mutableListOf<CarbonActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carbon_tracker)

        // Bind metrics cards
        cardCarbonSaved = findViewById(R.id.cardCarbonSaved)
        cardReductionRate = findViewById(R.id.cardReductionRate)
        cardEcoScore = findViewById(R.id.cardEcoScore)

        // Bind container
        activitiesContainer = findViewById(R.id.activitiesContainer)

        // Set top metrics cards values
        setMetricValues()

        // Initially display empty state or existing activities
        refreshActivitiesUI()
    }

    /** Sets the top metrics cards values using sample data or real backend values */
    private fun setMetricValues() {
        cardCarbonSaved.findViewById<TextView>(R.id.tvMetricValue).text =
            CarbonCalculator.formatImpact(42)
        cardReductionRate.findViewById<TextView>(R.id.tvMetricValue).text = "15% this month"
        cardEcoScore.findViewById<TextView>(R.id.tvMetricValue).text = "85/100"
    }

    /** Refresh Activities Section based on current list */
    private fun refreshActivitiesUI() {
        activitiesContainer.removeAllViews()

        if (activitiesList.isEmpty()) {
            // Inflate empty activities layout
            val emptyView = LayoutInflater.from(this)
                .inflate(R.layout.layout_activities_empty, activitiesContainer, false)

            val btnAdd = emptyView.findViewById<Button>(R.id.btnAddActivity)
            btnAdd.setOnClickListener { showAddActivityForm() }

            activitiesContainer.addView(emptyView)

        } else {
            // Inflate activities list layout
            val listView = LayoutInflater.from(this)
                .inflate(R.layout.layout_activities_list, activitiesContainer, false)

            activitiesRecyclerView = listView.findViewById(R.id.rvActivities)
            activitiesRecyclerView.layoutManager = LinearLayoutManager(this)
            activitiesAdapter = CarbonActivityAdapter(activitiesList)
            activitiesRecyclerView.adapter = activitiesAdapter

            // Handle Add Activity button in list header
            val btnAdd = listView.findViewById<Button>(R.id.btnAddActivityHeader)
            btnAdd.setOnClickListener { showAddActivityForm() }

            activitiesContainer.addView(listView)
        }
    }

    /** Show the form to add a new carbon activity */
    private fun showAddActivityForm() {
        val addView = LayoutInflater.from(this)
            .inflate(R.layout.layout_activities_add, activitiesContainer, false)

        val dropdownActivity = addView.findViewById<Spinner>(R.id.spinnerActivity)
        val btnCalculate = addView.findViewById<Button>(R.id.btnCalculateCarbon)
        val textCarbonImpact = addView.findViewById<TextView>(R.id.tvCalculatedCarbon)
        val dateInput = addView.findViewById<EditText>(R.id.inputDate)
        val btnSubmit = addView.findViewById<Button>(R.id.btnConfirmAddActivity)
        val btnCancel = addView.findViewById<Button>(R.id.btnCancel)

        // Setup activity dropdown with sample data
        val activities = listOf("Cover Cropping", "Solar Panels", "Tree Planting")
        dropdownActivity.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, activities)

        // Default date using DateUtils
        dateInput.setText(DateUtils.getCurrentDate())
        val calendar = Calendar.getInstance()

        // Show DatePickerDialog
        dateInput.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    dateInput.setText(DateUtils.getCurrentDate()) // Could also format selected date
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        // Calculate carbon impact using CarbonCalculator
        btnCalculate.setOnClickListener {
            val selectedActivity = dropdownActivity.selectedItem.toString()
            val impact = CarbonCalculator.calculateImpact(selectedActivity)
            textCarbonImpact.text = CarbonCalculator.formatImpact(impact)
        }

        // Add new activity
        btnSubmit.setOnClickListener {
            val name = dropdownActivity.selectedItem.toString()
            val date = dateInput.text.toString()
            val impactValue =
                textCarbonImpact.text.toString().replace(" kg CO₂", "").toIntOrNull() ?: 0

            val newActivity = CarbonActivity(
                name = name,
                date = date,
                carbonImpact = impactValue
            )

            activitiesList.add(newActivity)
            refreshActivitiesUI()
        }

        btnCancel.setOnClickListener { refreshActivitiesUI() }

        activitiesContainer.removeAllViews()
        activitiesContainer.addView(addView)
    }
}
