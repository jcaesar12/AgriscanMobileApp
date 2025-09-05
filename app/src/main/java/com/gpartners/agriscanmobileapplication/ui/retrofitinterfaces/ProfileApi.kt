package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import com.gpartners.agriscanmobileapplication.ui.profile.AddFinancialEntryDTO
import com.gpartners.agriscanmobileapplication.ui.profile.ChangePasswordDTO
import com.gpartners.agriscanmobileapplication.ui.profile.ChangePasswordResponseDTO
import com.gpartners.agriscanmobileapplication.ui.profile.FinancialEntryDTO
import com.gpartners.agriscanmobileapplication.ui.profile.FinancialSummaryDTO
import com.gpartners.agriscanmobileapplication.ui.profile.ProfileDTO
import com.gpartners.agriscanmobileapplication.ui.profile.RecordYieldDTO
import com.gpartners.agriscanmobileapplication.ui.profile.UpdateProfileDTO
import com.gpartners.agriscanmobileapplication.ui.profile.YieldDTO
import retrofit2.Call
import retrofit2.http.*

// Retrofit interface for Profile APIs
interface ProfileApi {

    // ---------------- PROFILE ----------------
    @GET("profile/{farmer_id}")
    fun getProfile(
        @Path("farmer_id") farmerId: Int
    ): Call<ProfileDTO>

    @PUT("profile/{farmer_id}")
    fun updateProfile(
        @Path("farmer_id") farmerId: Int,
        @Body data: UpdateProfileDTO
    ): Call<Map<String, Boolean>>

    // ---------------- PASSWORD ----------------
    @POST("profile/change-password/{farmer_id}")
    fun changePassword(
        @Path("farmer_id") farmerId: Int,
        @Body data: ChangePasswordDTO
    ): Call<ChangePasswordResponseDTO>

    // ---------------- FINANCIAL ----------------
    @GET("profile/financial-summary/{farmer_id}")
    fun getFinancialSummary(
        @Path("farmer_id") farmerId: Int
    ): Call<FinancialSummaryDTO>

    @GET("profile/financial-entries/{farmer_id}")
    fun getFinancialEntries(
        @Path("farmer_id") farmerId: Int
    ): Call<List<FinancialEntryDTO>>

    @POST("profile/add-financial-entry/{farmer_id}")
    fun addFinancialEntry(
        @Path("farmer_id") farmerId: Int,
        @Body data: AddFinancialEntryDTO,
        @Query("created_by_id") createdById: Int
    ): Call<Map<String, Boolean>>

    // ---------------- CROP YIELDS ----------------
    @GET("profile/yields/{farmer_id}")
    fun listYields(
        @Path("farmer_id") farmerId: Int
    ): Call<List<YieldDTO>>

    @POST("profile/record-yield/{farmer_id}")
    fun recordYield(
        @Path("farmer_id") farmerId: Int,
        @Body data: RecordYieldDTO,
        @Query("created_by_id") createdById: Int
    ): Call<Map<String, Boolean>>
}
