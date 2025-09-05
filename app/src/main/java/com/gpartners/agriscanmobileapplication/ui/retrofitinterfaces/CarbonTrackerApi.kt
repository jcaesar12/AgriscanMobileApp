package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.AddActivityDTO
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.AddActivityResponseDTO
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.CarbonImpactDTO
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.DashboardMetricsDTO
import com.gpartners.agriscanmobileapplication.ui.carbon_tracker.PastActivityDTO
import retrofit2.Call
import retrofit2.http.*

// Retrofit interface for Carbon Tracker APIs
interface CarbonTrackerApi {

    // ---------------- DASHBOARD METRICS ----------------
    @GET("carbon-tracker/dashboard/{farmer_id}")
    fun getDashboardMetrics(
        @Path("farmer_id") farmerId: Int
    ): Call<DashboardMetricsDTO>

    // ---------------- PAST ACTIVITIES ----------------
    @GET("carbon-tracker/past-activities/{farmer_id}")
    fun getPastActivities(
        @Path("farmer_id") farmerId: Int
    ): Call<List<PastActivityDTO>>

    // ---------------- ADD ACTIVITY ----------------
    @POST("carbon-tracker/add-activity/{farmer_id}")
    fun addActivity(
        @Path("farmer_id") farmerId: Int,
        @Body data: AddActivityDTO
    ): Call<AddActivityResponseDTO>

    // ---------------- CALCULATE CARBON IMPACT ----------------
    @GET("carbon-tracker/calculate-impact/{activity_id}")
    fun calculateImpact(
        @Path("activity_id") activityId: Int
    ): Call<CarbonImpactDTO>
}
