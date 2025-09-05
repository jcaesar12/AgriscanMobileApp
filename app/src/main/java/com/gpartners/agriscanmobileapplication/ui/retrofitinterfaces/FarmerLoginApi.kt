package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import com.gpartners.agriscanmobileapplication.ui.login_and_register.FarmerLoginDTO
import com.gpartners.agriscanmobileapplication.ui.login_and_register.FarmerLoginResponseDTO
import retrofit2.Call
import retrofit2.http.*

// Retrofit interface for Farmer Login APIs
interface FarmerLoginApi {

    // ---------------- LOGIN ----------------
    @POST("auth/login")
    fun loginFarmer(
        @Body loginData: FarmerLoginDTO
    ): Call<FarmerLoginResponseDTO>

    // ---------------- GET FARMER BY ID ----------------
    @GET("auth/farmer/{farmer_id}")
    fun getFarmerById(
        @Path("farmer_id") farmerId: Int
    ): Call<FarmerLoginResponseDTO>
}
